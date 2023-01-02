package org.carth.aoc21

import org.carth.common.Puzzle

class Day09(input: String) : Puzzle<Int, Int>() {

    private val data = input.lines()
    private val lSize = data.size + 2
    private val cSize = data[0].length + 2

    private fun getGrid(): List<IntArray> {
        val grid = List(lSize) { IntArray(cSize) { 9 } }
        data.forEachIndexed { l, line ->
            line.toList().map { it.code - 48 }.toIntArray().copyInto(grid[l + 1], 1)
        }
        return grid
    }

    override fun solvePartOne(): Int {
        val grid = getGrid()
        var lows = 0
        for (l in 1 until lSize) {
            for (c in 1 until cSize) {
                if (isSmaller(grid, l, c)) {
                    lows += grid[l][c] + 1
                }
            }
        }
        return lows
    }

    private fun isSmaller(grid: List<IntArray>, l: Int, c: Int): Boolean {
        val p = grid[l][c]
        return (p < grid[l - 1][c]) && (p < grid[l + 1][c]) && (p < grid[l][c - 1]) && (p < grid[l][c + 1])
    }

    private fun getBasin(
        grid: List<IntArray>,
        l: Int,
        c: Int,
        checked: MutableSet<Coord> = mutableSetOf()
    ): Int {
        listOf(
            Coord(l - 1, c),
            Coord(l + 1, c),
            Coord(l, c + 1),
            Coord(l, c - 1)
        )
            .filter { !checked.contains(it) && grid[it.l][it.c] < 9 }
            .forEach {
                checked.add(it)
                getBasin(grid, it.l, it.c, checked)
            }
        return checked.size
    }

    override fun solvePartTwo(): Int {
        val grid = getGrid()
        val basins = mutableListOf<Int>()
        for (l in 1 until lSize) {
            for (c in 1 until cSize) {
                if (isSmaller(grid, l, c)) {
                    basins.add(getBasin(grid, l, c))
                }
            }
        }
        return basins.sortedDescending().take(3).reduce { total, item -> total * item }
    }

    data class Coord(val l: Int, val c: Int)
}