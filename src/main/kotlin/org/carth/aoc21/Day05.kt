package org.carth.aoc21

import org.carth.common.Puzzle
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set
import org.jetbrains.kotlinx.multik.ndarray.operations.filter
import java.lang.Integer.max

class Day05(input: String) : Puzzle<String, String>() {

    private val data = input.lines()

    class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int)

    private fun getLines(): Pair<List<Line>, Int> {
        val p = Regex("\\d+")
        var gridSize = 0
        val list = data.map { line ->
            val m = p.findAll(line)
            val (x1, y1, x2, y2) = m.map { it.value.toInt() }.toList()
            gridSize = max(max(max(max(gridSize, x1), x2), y1), y2)
            Line(x1, y1, x2, y2)
        }
        return Pair(list, gridSize + 1)
    }

    override fun solvePartOne(): String {
        val (lines, gridSize) = getLines()
        val grid = mk.zeros<Int>(gridSize, gridSize)
        lines.forEach { l ->
            if (l.x1 == l.x2) {
                // row
                for (i in minOf(l.y1, l.y2)..maxOf(l.y1, l.y2)) {
                    grid[i, l.x1] += 1
                }
            } else if (l.y1 == l.y2) {
                // column
                for (i in minOf(l.x1, l.x2)..maxOf(l.x1, l.x2)) {
                    grid[l.y1, i] += 1
                }
            }
        }
        val total = grid.filter { it > 1 }.size
        return total.toString()
    }

    override fun solvePartTwo(): String {
        val (lines, gridSize) = getLines()
        val grid = mk.zeros<Int>(gridSize, gridSize)
        lines.forEach { l ->
            if (l.x1 == l.x2) {
                // row
                for (i in minOf(l.y1, l.y2)..maxOf(l.y1, l.y2)) {
                    grid[i, l.x1] += 1
                }
            } else if (l.y1 == l.y2) {
                // column
                for (i in minOf(l.x1, l.x2)..maxOf(l.x1, l.x2)) {
                    grid[l.y1, i] += 1
                }
            } else {
                // diagonal
                val startX = minOf(l.x1, l.x2)
                var (j, endY) = if (startX == l.x1) Pair(l.y1, l.y2) else Pair(l.y2, l.y1)
                val inc = if (j < endY) 1 else -1
                for (i in startX..maxOf(l.x1, l.x2)) {
                    grid[j, i] += 1
                    j += inc
                }
            }
        }
        val total = grid.filter { it > 1 }.size
        return total.toString()
    }
}