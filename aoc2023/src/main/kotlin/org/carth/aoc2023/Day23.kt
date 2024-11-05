package org.carth.aoc2023

import org.carth.common.GridBoolean
import org.carth.common.GridChar
import org.carth.common.Point2d
import org.carth.common.Puzzle

typealias FnNeighbours = (Point2d) -> List<Pair<Point2d, Int>>

class Day23(input: String) : Puzzle<Int, Int>() {

    private val grid = GridChar(input)
    private val start = Point2d(grid[0].indexOf('.'), 0)
    private val end = Point2d(grid.last().lastIndexOf('.'), grid.height - 1)

    override fun solvePartOne(): Int {
        val visited = GridBoolean(grid.width, grid.height)
        return findMax(start, visited, 0) { current ->
            when (grid[current]) {
                '>' -> listOf(current + Point2d.RIGHT to 1)
                '<' -> listOf(current + Point2d.LEFT to 1)
                'v' -> listOf(current + Point2d.DOWN to 1)
                '^' -> listOf(current + Point2d.UP to 1)
                else -> {
                    current.neighbours().map { it to 1 }
                }
            }
        }
    }

    private fun Point2d.neighbours() = this.cardinalAdjacent().filter { grid.isInside(it)  && grid[it] in ".<>^v" }

    override fun solvePartTwo(): Int {
        val crossings = mutableMapOf(start to mutableListOf<Pair<Point2d, Int>>(), end to mutableListOf())

        for (y in grid.heightIndices) {
            for (x in grid.widthIndices) {
                val point = Point2d(x, y)
                if (grid[point] == '.') {
                    if (point.neighbours().size > 2) {
                        crossings[point] = mutableListOf()
                    }
                }
            }
        }

        for (crossing in crossings.keys) {
            var points = setOf(crossing)
            val visited = mutableSetOf(crossing)
            var distance = 0
            while (points.isNotEmpty()) {
                distance++
                points = buildSet {
                    for (point in points) {
                        point.neighbours().filter { it !in visited }
                            .forEach { neighbour ->
                                if (neighbour in crossings) {
                                    crossings.getValue(crossing).add(neighbour to distance)
                                } else {
                                    add(neighbour)
                                    visited.add(neighbour)
                                }
                            }
                    }
                }
            }
        }

        val visited = GridBoolean(grid.width, grid.height)
        return findMax(start, visited, 0) { current ->
            crossings.getValue(current)
        }
    }


    private fun findMax(current: Point2d, visited: GridBoolean, distance: Int, neighbours: FnNeighbours): Int {
        if (current == end) {
            return distance
        }
        visited[current] = true
        val max = neighbours(current)
            .filter { (neighbour, _) -> !visited[neighbour.y][neighbour.x] }
            .maxOfOrNull { (neighbour, weight) ->
                findMax(neighbour, visited, distance + weight, neighbours)
            }
        visited[current] = false
        return max ?: 0
    }
}