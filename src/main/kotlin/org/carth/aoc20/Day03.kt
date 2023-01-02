package org.carth.aoc20

import org.carth.common.Point
import org.carth.common.Puzzle
import org.carth.common.extensions.get


class Day03(input: String) : Puzzle<String, String>() {

    private val data = input.lines()

    override fun solvePartOne(): String {
        return solve(Point(3, 1)).toString()
    }

    override fun solvePartTwo(): String {
        val slopes = listOf(Point(1, 1), Point(3, 1), Point(5, 1), Point(7, 1), Point(1, 2))
        return slopes.map { slope ->
            solve(slope)
        }.reduce { acc, i -> acc * i }.toString()
    }

    private fun solve(slope: Point): Int {
        var p = Point(0, 0)
        val w = data[0].length
        var trees = 0
        while (p.y < data.size) {
            if (data[p] == '#') trees++
            p += slope
            p = Point(p.x.mod(w), p.y)
        }
        return trees
    }
}