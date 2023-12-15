package org.carth.aoc2023

import org.carth.common.MutableGridString
import org.carth.common.Puzzle

class Day14(input: String) : Puzzle<Int, Int>() {

    private val grid = MutableGridString(input)
    private val width = grid.width
    private val height = grid.height

    override fun solvePartOne(): Int {
        tiltNorth()
        return calc()
    }

    override fun solvePartTwo(): Int {

        val cache = mutableMapOf<String, Int>()
        var index = 0
        val total = 1_000_000_000
        var remaining = false

        while (index < total) {
            if (!remaining) {
                val current = grid.lines.joinToString("") { it.concatToString() }
                if (current in cache) {
                    val length = index - cache[current]!!
                    val remainingCycles = (total - index) % length
                    index = total - remainingCycles
                    remaining = true
                } else {
                    cache[current] = index
                }
            }
            tiltNorth()
            tiltWest()
            tiltSouth()
            tiltEast()
            index++
        }
        return calc()
    }

    private fun tiltNorth() {
        (0..<height).forEach { y ->
            (0..<width).forEach { x ->
                if (grid[x, y] == 'O') {
                    var yy = y
                    while (yy > 0 && grid[x, yy - 1] == '.') yy--
                    grid[x, y] = '.'
                    grid[x, yy] = 'O'
                }
            }
        }
    }

    private fun tiltWest() {
        (0..<height).forEach { y ->
            (0..<width).forEach { x ->
                if (grid[x, y] == 'O') {
                    var xx = x
                    while (xx > 0 && grid[xx - 1, y] == '.') xx--
                    grid[x, y] = '.'
                    grid[xx, y] = 'O'
                }
            }
        }
    }

    private fun tiltSouth() {
        (height - 1 downTo 0).forEach { y ->
            (0..<width).forEach { x ->
                if (grid[x, y] == 'O') {
                    var yy = y
                    while (yy < height - 1 && grid[x, yy + 1] == '.') yy++
                    grid[x, y] = '.'
                    grid[x, yy] = 'O'
                }
            }
        }
    }

    private fun tiltEast() {
        (0..<height).forEach { y ->
            (width - 1 downTo 0).forEach { x ->
                if (grid[x, y] == 'O') {
                    var xx = x
                    while (xx < width - 1 && grid[xx + 1, y] == '.') xx++
                    grid[x, y] = '.'
                    grid[xx, y] = 'O'
                }
            }
        }
    }

    private fun calc(): Int {
        var total = 0
        for (y in 0..<height)
            for (x in 0..<width) {
                if (grid[x, y] == 'O') {
                    total += width - y
                }
            }
        return total
    }

}