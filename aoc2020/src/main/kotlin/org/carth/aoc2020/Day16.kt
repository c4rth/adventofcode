package org.carth.aoc2020

import org.carth.common.Puzzle

class Day16(input: String) : Puzzle<Int, Long>() {

    private val blocks = input.split(System.lineSeparator() + System.lineSeparator())
    private val fields = blocks[0].lines().associate { line ->
        val split = line.split(": ", " or ")
        split[0] to listOf(split[1].toRange(), split[2].toRange())
    }
    private val allRanges = fields.values.flatten()
    private val nearbys = blocks[2].toListOfListOfInt()

    override fun solvePartOne(): Int {
        return nearbys.sumOf { nearby ->
            nearby.filter { value ->
                allRanges.none { range -> value in range }
            }.sum()
        }
    }

    override fun solvePartTwo(): Long {
        val ticket = blocks[1].toListOfListOfInt().first()
        val valids = nearbys.filter { nearby ->
            nearby.all { value -> allRanges.any { range -> value in range } }
        }
        val possibleFields: Map<String, MutableSet<Int>> = fields.keys.associateWith { name ->
            ticket.indices.filter { i ->
                valids.all { valid ->
                    fields.getValue(name).any { rule -> valid[i] in rule }
                }
            }.toMutableSet()
        }
        val foundFields = filterPossibleFields(possibleFields)
        return foundFields.filter { entry -> entry.key.startsWith("departure") }
            .map { ticket[it.value].toLong() }
            .reduce { acc, value -> acc * value }
    }

    private fun filterPossibleFields(possibleFields: Map<String, MutableSet<Int>>): Map<String, Int> {
        val filtered = mutableMapOf<String, Int>()
        while (filtered.size < possibleFields.size) {
            possibleFields.entries
                .filter { (_, values) -> values.size == 1 }
                .forEach { (name, values) ->
                    val column = values.first()
                    filtered[name] = column
                    possibleFields.values.forEach { allValues -> allValues.remove(column) }
                }
        }
        return filtered
    }

    private fun String.toListOfListOfInt() = this.lines().drop(1).map { line -> line.split(",").map { it.toInt() } }

    private fun String.toRange() = this.split("-").map { it.toInt() }.let { it[0]..it[1] }

}
