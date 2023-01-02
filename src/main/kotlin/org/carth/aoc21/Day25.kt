package org.carth.aoc21

import org.carth.common.Puzzle

class Day25(input: String) : Puzzle<Int, Int>() {

    private val data = input.lines()

    private fun parse(input: List<String>): Array<CharArray> =
        input.map { line -> line.toCharArray() }.toTypedArray()

    private fun getPossibleMoves(
        grid: Array<CharArray>,
        char: Char,
        isFree: (Int, Int) -> Boolean
    ): List<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()
        grid.forEachIndexed { l, line ->
            line.forEachIndexed { c, direction ->
                if (direction == char && isFree(l, c)) list.add(l to c)
            }
        }
        return list
    }

    override fun solvePartOne(): Int {
        val grid = parse(data)
        var numberOfMoves: Int
        var times = 0
        val maxL = grid.size
        val maxC = grid[0].size
        do {
            numberOfMoves = 0
            // move east
            getPossibleMoves(grid, '>') { l, c ->
                grid[l][(c + 1) % maxC] == '.'
            }.forEach { (l, c) ->
                grid[l][c] = '.'
                grid[l][(c + 1) % maxC] = '>'
                numberOfMoves++
            }
            // move south
            getPossibleMoves(grid, 'v') { l, c ->
                grid[(l + 1) % maxL][c] == '.'
            }.forEach { (l, c) ->
                grid[l][c] = '.'
                grid[(l + 1) % maxL][c] = 'v'
                numberOfMoves++
            }
            times++
        } while (numberOfMoves > 0)
        return times
    }

    override fun solvePartTwo() = 0
}