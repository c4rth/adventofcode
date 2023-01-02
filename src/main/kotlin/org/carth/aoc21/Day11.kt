package org.carth.aoc21

import org.carth.common.Puzzle

class Day11(input: String) : Puzzle<String, String>() {
    private val data = input.lines()
    override fun solvePartOne(): String {
        val grid = Grid(data)
        var total = 0
        var toExplode: ArrayDeque<Coord>
        for (index in 1..100) {
            toExplode = grid.increaseLevel()
            while (toExplode.isNotEmpty()) {
                val coord = toExplode.removeFirst()
                total += 1
                toExplode += grid.explodeAround(coord)
            }
        }
        return total.toString()
    }

    override fun solvePartTwo(): String {
        val grid = Grid(data)
        var toExplode: ArrayDeque<Coord>
        var index = 0
        while (true) {
            index += 1
            toExplode = grid.increaseLevel()
            while (toExplode.isNotEmpty()) {
                val coord = toExplode.removeFirst()
                toExplode += grid.explodeAround(coord)
            }
            if (grid.isSynchro()) return index.toString()
        }
    }


    class Coord(val l: Int, val c: Int)

    class Grid(data: List<String>) {
        private val innerGrid = List(12) { IntArray(12) { 100 } }

        init {
            data.forEachIndexed { l, line ->
                line.toList().map { it.code - 48 }.toIntArray().copyInto(innerGrid[l + 1], 1)
            }
        }

        fun isSynchro(): Boolean {
            for (l in 1..10) {
                for (c in 1..10) {
                    if (innerGrid[l][c] != 0) return false
                }
            }
            return true
        }

        fun increaseLevel(): ArrayDeque<Coord> {
            val toExplode = ArrayDeque<Coord>()
            for (l in 1..10) {
                for (c in 1..10) {
                    innerGrid[l][c] += 1
                    if (innerGrid[l][c] == 10) {
                        toExplode.add(Coord(l, c))
                        innerGrid[l][c] = 0
                    }
                }
            }
            return toExplode
        }

        fun explodeAround(coord: Coord): ArrayDeque<Coord> {
            val toExplode = ArrayDeque<Coord>()
            toExplode += explode(coord.l - 1, coord.c - 1)
            toExplode += explode(coord.l - 1, coord.c)
            toExplode += explode(coord.l - 1, coord.c + 1)
            toExplode += explode(coord.l, coord.c - 1)
            toExplode += explode(coord.l, coord.c + 1)
            toExplode += explode(coord.l + 1, coord.c - 1)
            toExplode += explode(coord.l + 1, coord.c)
            toExplode += explode(coord.l + 1, coord.c + 1)
            return toExplode
        }

        private fun explode(l: Int, c: Int): ArrayDeque<Coord> {
            val toExplode = ArrayDeque<Coord>()
            if (innerGrid[l][c] > 0) {
                innerGrid[l][c] += 1
                if (innerGrid[l][c] == 10) {
                    toExplode.add(Coord(l, c))
                    innerGrid[l][c] = 0
                }
            }
            return toExplode
        }
    }

}
