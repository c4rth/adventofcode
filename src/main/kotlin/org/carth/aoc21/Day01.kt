package org.carth.aoc21

import org.carth.common.Puzzle

class Day01(input: String) : Puzzle<String, String>() {

    private val data = input.lines()

    override fun solvePartOne(): String {
        return data.map { it.toInt() }
            .windowed(2)
            .filter { (a, b) -> a < b }
            .size
            .toString()
    }

    override fun solvePartTwo(): String {

        return data.map { it.toInt() }.windowed(3)
            .map { it[0] + it[1] + it[2] }
            .windowed(2)
            .filter { (a, b) -> a < b }
            .size
            .toString()
    }
}