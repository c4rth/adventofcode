package org.carth.aoc20

import org.carth.common.Point
import org.carth.common.Puzzle

class Day11(input: String) : Puzzle<Int, Int>() {
    private var data = input.lines().map { it.toMutableList() }.toMutableList()
    private val heigth = data.size
    private val width = data[0].size
    override fun solvePartOne() = solve(4) { p -> listAdjacents(p, true) }
    override fun solvePartTwo() = solve(5) { p -> listAdjacents(p, false) }

    private fun solve(maxOccupied: Int, list: (Point) -> List<Char>): Int {
        var total = 0
        var prevTotal: Int
        do {
            val nextData = MutableList(heigth) { MutableList(width) { ' ' } }
            prevTotal = total
            for (l in 0 until heigth) {
                for (c in 0 until width) {
                    val p = Point(c, l)
                    if (data[p] == 'L') {
                        if (!list(p).contains('#')) {
                            nextData[p] = '#'
                        } else {
                            nextData[p] = 'L'
                        }
                    } else if (data[p] == '#') {
                        if (list(p).count { it == '#' } >= maxOccupied) {
                            nextData[p] = 'L'
                        } else {
                            nextData[p] = '#'
                        }
                    } else {
                        nextData[p] = '.'
                    }
                }
            }
            total = nextData.sumOf { line -> line.count { s -> s == '#' } }
            data = nextData
        } while (total != prevTotal)
        return total
    }

    private fun listAdjacents(p: Point, direct: Boolean): List<Char> {
        val dirs = listOf(Point.N, Point.NE, Point.E, Point.SE, Point.S, Point.SW, Point.W, Point.NW)
        val list = mutableListOf<Char>()
        dirs.forEach { dir ->
            var curr = p
            do {
                curr += dir
            } while (!direct && (curr.isInside(width, heigth) && data[curr] == '.'))
            if (curr.isInside(width, heigth)) {
                list.add(data[curr])
            }
        }
        return list
    }

    private operator fun List<List<Char>>.get(point: Point): Char = this[point.y][point.x]
    private operator fun MutableList<MutableList<Char>>.set(point: Point, c: Char) {
        this[point.y][point.x] = c
    }

}
