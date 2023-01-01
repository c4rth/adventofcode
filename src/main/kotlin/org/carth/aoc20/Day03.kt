package org.carth.aoc20

import org.carth.common.extensions.get
import org.carth.common.Point
import org.carth.common.Puzzle


class Day03(private val data: List<String>) : Puzzle<Int, Int>() {

    override fun solvePartOne(): Int {
        return solve(Point(3,1))
    }

    override fun solvePartTwo(): Int {
        val slopes = listOf(Point(1,1), Point(3,1), Point(5,1), Point(7,1), Point(1,2))
        return slopes.map { slope ->
            solve(slope)
        }.reduce { acc, i -> acc * i }
    }

    private fun solve(slope: Point ): Int {
        var p = Point(0,0)
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