package org.carth.aoc2023

import org.carth.common.GridChar
import org.carth.common.Point2d
import org.carth.common.Puzzle

class Day03(data: String) : Puzzle<Long, Long>() {

    private val grid = GridChar(data)

    override fun solvePartOne(): Long {
        val pointNumbers = emptyMap<Point2d, Long>().toMutableMap()
        grid.lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char != '.' && !char.isDigit()) {
                    pointNumbers += getAdjacentNumbers(x, y)
                }
            }
        }
        return pointNumbers.values.reduce { acc, value -> acc + value }
    }

    override fun solvePartTwo(): Long {
        var total = 0L
        grid.lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char == '*') {
                    getAdjacentNumbers(x, y).let { pointNumbers ->
                        if (pointNumbers.size == 2) {
                            total += pointNumbers.values.reduce { acc, value -> acc * value }
                        }
                    }
                }
            }
        }
        return total
    }

    private fun getAdjacentNumbers(x: Int, y: Int): Map<Point2d, Long> {
        val pointNumbers = mutableMapOf<Point2d, Long>()
        grid.adjacent(x, y).forEach { p ->
            if (grid[p].isDigit()) {
                getPointAndNumber(p).let { (point, value) -> pointNumbers[point] = value }
            }
        }
        return pointNumbers
    }

    private fun getPointAndNumber(p: Point2d): Pair<Point2d, Long> {
        val line = " " + grid[p.y].joinToString("") + " "
        var startC = p.x + 1
        while (line[startC - 1].isDigit()) startC -= 1
        var endC = p.x + 2
        while (line[endC].isDigit()) endC += 1
        return Point2d(startC, p.y) to line.substring(startC, endC).toLong()
    }
}