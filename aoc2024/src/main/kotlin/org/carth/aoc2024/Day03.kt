package org.carth.aoc2024

import org.carth.aoc.Data
import org.carth.utils.GridChar
import org.carth.utils.Point2d
import org.carth.aoc.Puzzle

fun main() = Day03().solve()

class Day03 : Puzzle<Long, Long>() {

    @Sample(expected = "4361")
    @Puzzle(expected = "543867")
    override fun solvePart1(data: Data): Long {
        val grid = GridChar(data)
        val pointNumbers = emptyMap<Point2d, Long>().toMutableMap()
        grid.lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char != '.' && !char.isDigit()) {
                    pointNumbers += grid.getAdjacentNumbers(x, y)
                }
            }
        }
        return pointNumbers.values.reduce { acc, value -> acc + value }
    }

    @Sample(expected = "467835")
    @Puzzle(expected = "79613331")
    override fun solvePart2(data: Data): Long {
        val grid = GridChar(data)
        var total = 0L
        grid.lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char == '*') {
                    grid.getAdjacentNumbers(x, y).let { pointNumbers ->
                        if (pointNumbers.size == 2) {
                            total += pointNumbers.values.reduce { acc, value -> acc * value }
                        }
                    }
                }
            }
        }
        return total
    }

    private fun GridChar.getAdjacentNumbers(x: Int, y: Int): Map<Point2d, Long> {
        val pointNumbers = mutableMapOf<Point2d, Long>()
        this.adjacent(x, y).forEach { p ->
            if (this[p].isDigit()) {
                getPointAndNumber(p).let { (point, value) -> pointNumbers[point] = value }
            }
        }
        return pointNumbers
    }

    private fun GridChar.getPointAndNumber(p: Point2d): Pair<Point2d, Long> {
        val line = " " + this[p.y].joinToString("") + " "
        var startC = p.x + 1
        while (line[startC - 1].isDigit()) startC -= 1
        var endC = p.x + 2
        while (line[endC].isDigit()) endC += 1
        return Point2d(startC, p.y) to line.substring(startC, endC).toLong()
    }
}