package org.carth.aoc20

import org.carth.common.Puzzle

class Day06(input: String) : Puzzle<String, String>() {

    private val allAnswers =
        input.split(System.lineSeparator() + System.lineSeparator()).map { it.split(System.lineSeparator()) }

    override fun solvePartOne(): String {
        return solve(1).toString()
    }

    override fun solvePartTwo(): String {
        return solve(2).toString()
    }

    private fun solve(part: Int): Int {
        var total = 0
        allAnswers.forEach { answers ->
            total += if (answers.size == 1) {
                answers[0].length
            } else {
                val map = mutableMapOf<Char, Int>()
                answers.forEach { answer ->
                    answer.forEach { c ->
                        map[c] = map.getOrDefault(c, 0) + 1
                    }
                }
                if (part == 1)
                    map.size
                else
                    map.filter { (_, num) -> num == answers.size }.size
            }
        }
        return total
    }
}