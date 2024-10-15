package org.carth.aoc2020

import org.carth.common.Point2d
import org.carth.common.Puzzle


class Day03(input: String) : Puzzle<Int, Int>() {

    private val data = input.lines()

    override fun solvePartOne(): Int {
        return solve(Point2d(3, 1))
    }

    override fun solvePartTwo(): Int {
        val slopes = listOf(Point2d(1, 1), Point2d(3, 1), Point2d(5, 1), Point2d(7, 1), Point2d(1, 2))
        return slopes.map { slope ->
            solve(slope)
        }.reduce { acc, i -> acc * i }
    }

    private fun solve(slope: Point2d): Int {
        var p = Point2d(0, 0)
        val w = data[0].length
        var trees = 0
        while (p.y < data.size) {
            if (data[p.y][p.x] == '#') trees++
            p += slope
            p = Point2d(p.x.mod(w), p.y)
        }
        return trees
    }
}