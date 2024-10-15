package org.carth.aoc2023

import org.carth.common.Puzzle

class Day19(input: String) : Puzzle<Long, Long>() {

    private val data = input.split(System.lineSeparator() + System.lineSeparator())

    private val workflows = data[0].lines().associate { line ->
        val split = line.dropLast(1).split("{", ",")
        split[0] to Workflow(split[0], split.drop(1).map { str ->
            val splitWorkflow = str.split(":")
            if (splitWorkflow.size > 1) {
                val cond = splitWorkflow[0]
                Rule(Condition(cond[0], cond[1], splitWorkflow[0].drop(2).toInt()), splitWorkflow[1])
            } else {
                Rule(null, str)
            }
        }
        )
    }
    private val ratings = data[1].lines().map { line ->
        val split = line.removeSurrounding("{", "}").split(",")
        Rating(split.associate { y ->
            val z = y.split("=")
            z[0] to z[1].toInt()
        })
    }

    data class Part(val vs: Map<Char, Int>) {
        operator fun get(v: Char): Int = vs[v]!!
    }

    override fun solvePartOne(): Long {
        var total = 0L
        for (rating in ratings) {
            var workflowKey = "in"
            while (workflowKey != "A" && workflowKey != "R") {
                val currentWorkflow = workflows.getValue(workflowKey)
                for (rule in currentWorkflow.rules) {
                    val condition = rule.condition
                    if (condition != null) {
                        val value = rating.ratings[condition.value.toString()]!!
                        val ok = if (condition.comp == '<') value < condition.limit else value > condition.limit
                        if (!ok) continue
                    }
                    workflowKey = rule.destination
                    break
                }
            }
            if (workflowKey == "A") total += rating.sum()
        }
        return total
    }

    private fun solve(ranges: Map<Char, IntRange>, workflowKey: String): Long {
        if (workflowKey == "R") return 0
        var total = 0L
        if (workflowKey == "A") {
            total = 1L
            for (range in ranges.values) total *= range.last - range.first + 1
            return total
        }
        val currentWorkflow = workflows.getValue(workflowKey)
        val currentRanges = ranges.toMutableMap()
        for (rule in currentWorkflow.rules) {
            val condition = rule.condition
            if (condition == null) {
                total += solve(currentRanges, rule.destination)
                break
            } else {
                val range = currentRanges.getValue(condition.value)
                val (range1, range2) = if (condition.comp == '<') {
                    range.first..minOf(range.last, condition.limit - 1) to
                            maxOf(range.first, condition.limit)..range.last
                } else {
                    maxOf(range.first, condition.limit + 1)..range.last to
                            range.first..minOf(range.last, condition.limit)
                }
                if (!range1.isEmpty()) {
                    val nextRanges = currentRanges.toMutableMap()
                    nextRanges[condition.value] = range1
                    total += solve(nextRanges, rule.destination)
                }
                if (!range2.isEmpty())
                    currentRanges[condition.value] = range2
            }
        }
        return total
    }

    override fun solvePartTwo(): Long {
        return solve(mapOf('x' to 1..4000, 'm' to 1..4000, 'a' to 1..4000, 's' to 1..4000), "in")
    }

    data class Workflow(val key: String, val rules: List<Rule>)

    data class Rule(val condition: Condition?, val destination: String)

    data class Condition(val value: Char, val comp: Char, val limit: Int)

    data class Rating(val ratings: Map<String, Int>) {
        fun sum() = ratings.values.sum()
    }

}