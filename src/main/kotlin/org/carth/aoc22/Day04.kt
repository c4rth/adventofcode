package org.carth.aoc22

import org.carth.common.Puzzle

class Day04(data: List<String>) : Puzzle<Int, Int>() {

    private val ranges = getPairElves(data)

    override fun solvePartOne() = ranges.count { (r1, r2) -> r1.includes(r2) || r2.includes(r1) }

    override fun solvePartTwo() = ranges.count { (r1, r2) -> r1.isOverlap(r2) }

    private fun getPairElves(data: List<String>): List<Pair<Range, Range>> {
        return data.map { line ->
            line.split(",", "-")
                .map(String::toInt)
        }.map { ints ->
            Pair(Range(ints[0]..ints[1]), Range(ints[2]..ints[3]))
        }
    }

    class Range(private val range: IntRange) {
        fun includes(range2: Range) = range.first <= range2.range.first && range.last >= range2.range.last

        fun isOverlap(range2: Range) = range.intersect(range2.range).isNotEmpty()

    }
}