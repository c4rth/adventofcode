package org.carth.aoc20

import org.carth.common.Puzzle

class Day05(private val data: List<String>) : Puzzle<Int, Int>() {

    override fun solvePartOne(): Int {
        return data.maxOf { line ->
            mapSeatId(line)
        }
    }

    override fun solvePartTwo(): Int {
        return data.map { line ->
            mapSeatId(line)
        }.sorted()
            .zipWithNext()
            .first { it.second - it.first != 1 }
            .first + 1
    }

    private fun mapSeatId(line: String): Int {
        val row = compute(line.take(7),  127, 'F')
        val col = compute(line.drop(7),  7, 'L')
        return row.first * 8 + col.first
    }

    private fun compute(input: String, to: Int, lower: Char): Pair<Int, Int> {
        var range = Pair(0, to)
        input.forEach { c ->
            range = if (c == lower)
                Pair(range.first, range.first + ((range.second - range.first + 1) / 2 - 1))
            else
                Pair(range.first + ((range.second - range.first + 1) / 2), range.second)
        }
        return range
    }
}