package org.carth.aoc22

import org.carth.common.Point
import org.carth.common.Puzzle
import kotlin.math.min

class Day24(input: String) : Puzzle<Int, Int>() {

    private val data = input.lines()

    override fun solvePartOne(): Int {
        val map = PuzzleMap.parse(data)
        val start = map.start
        val end = map.end
        return map.getPathTime(start, end).first
    }

    override fun solvePartTwo(): Int {
        var map = PuzzleMap.parse(data)
        val start = map.start
        val end = map.end
        var total = 0

        map.getPathTime(start, end).also { (time, newMap) ->
            total += time
            map = newMap
        }
        map.getPathTime(end, start).also { (time, newMap) ->
            total += time
            map = newMap
        }
        map.getPathTime(start, end).also { (time, newMap) ->
            total += time
            map = newMap
        }
        return total
    }

    class PuzzleMap {
        private val _map: MutableList<MutableList<MutableList<Char>>>

        constructor() {
            _map = mutableListOf()
        }

        constructor(width: Int, height: Int) {
            _map = MutableList(height) { MutableList(width) { mutableListOf() } }
        }

        private val width: Int
            get() = _map[0].size

        private val height: Int
            get() = _map.size

        val start: Point
            get() = Point(_map[0].indexOfFirst { it[0] == EMPTY }, 0)

        val end: Point
            get() = Point(_map[height - 1].indexOfFirst { it[0] == EMPTY }, height - 1)

        private fun get(point: Point) = _map[point.y][point.x]

        private fun moveBlizzards(): PuzzleMap {
            val newMap = PuzzleMap(width, height)

            fun moveBlizzard(blizzard: Char, step: Point, edge: Point) {
                if (get(step).first() == WALL) {
                    newMap.get(edge).add(blizzard)
                } else {
                    newMap.get(step).add(blizzard)
                }
            }

            _map.forEachIndexed { y, currentRow ->
                currentRow.forEachIndexed { x, areas ->
                    val current = Point(x, y)
                    areas.forEach { char ->
                        when (char) {
                            WALL -> newMap.get(current).add(char)
                            EMPTY -> {}
                            BLIZZARD_UP -> moveBlizzard(BLIZZARD_UP, current + Point.UP, Point(x, height - 2))
                            BLIZZARD_DOWN -> moveBlizzard(BLIZZARD_DOWN, current + Point.DOWN, Point(x, 1))
                            BLIZZARD_RIGHT -> moveBlizzard(BLIZZARD_RIGHT, current + Point.RIGHT, Point(1, y))
                            BLIZZARD_LEFT -> moveBlizzard(BLIZZARD_LEFT, current + Point.LEFT, Point(width - 2, y))
                        }
                    }
                }
            }

            _map.forEachIndexed { y, currentRow ->
                currentRow.forEachIndexed { x, _ ->
                    if (newMap._map[y][x].isEmpty()) newMap._map[y][x].add(EMPTY)
                }
            }

            return newMap
        }

        fun getPathTime(start: Point, end: Point): Pair<Int, PuzzleMap> {
            var currentLocations: MutableMap<Point, Int> = mutableMapOf(start to 0)
            var newMap = this

            while (!currentLocations.containsKey(end)) {
                newMap = newMap.moveBlizzards()
                val possibleMoves: MutableMap<Point, Int> = mutableMapOf()

                // for every location we're in, wait, and move them
                currentLocations.forEach { (location: Point, cost: Int) ->
                    // wait, n, s, e, w
                    listOf(Point.ZERO, Point.LEFT, Point.RIGHT, Point.UP, Point.DOWN).forEach { move ->
                        val new = location + move
                        if (new.isInside(this.width, this.height) &&
                            newMap._map[new.y][new.x].size == 1 &&
                            newMap._map[new.y][new.x].first() == EMPTY
                        ) {
                            possibleMoves.merge(new, cost + 1) { a, b -> min(a, b) }
                        }
                    }
                }

                currentLocations = possibleMoves
            }
            return currentLocations[end]!! to newMap
        }

        companion object {
            fun parse(input: List<String>): PuzzleMap {
                val map = PuzzleMap()
                input.forEach { line ->
                    val row = mutableListOf<MutableList<Char>>()
                    line.forEach { c ->
                        row.add(mutableListOf(c))
                    }
                    map._map.add(row)
                }
                return map
            }

            const val EMPTY = '.'
            const val WALL = '#'
            const val BLIZZARD_LEFT = '<'
            const val BLIZZARD_RIGHT = '>'
            const val BLIZZARD_DOWN = 'v'
            const val BLIZZARD_UP = '^'
        }
    }

}