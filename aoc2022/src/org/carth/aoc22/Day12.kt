package org.carth.aoc22

import org.carth.common.GridChar
import org.carth.common.Point2d
import org.carth.common.Puzzle
import org.carth.common.findShortestPath

class Day12(input: String) : Puzzle<Int, Int>() {


    private val grid = GridChar(input)

    override fun solvePartOne(): Int {
        val (start, end) = getStartEnd()

        val shortestPath = findShortestPath(
            start = start,
            end = end,
            neighbours = { point ->
                point.cardinalAdjacent().filter { p -> p.isInside(grid) }
                    .filter { grid[it].value() - grid[point].value() <= 1 }
            }
        )
        return shortestPath.getScore()
    }

    override fun solvePartTwo(): Int {
        val (_, end) = getStartEnd()
        val paths = mutableListOf<Int>()
        for (y in grid.heightIndices) {
            for (x in grid.widthIndices) {
                if (grid[x, y] == 'a' || grid[x, y] == 'S') {
                    val shortestPath = findShortestPath(
                        start = Point2d(x, y),
                        end = end,
                        neighbours = { point ->
                            point.cardinalAdjacent().filter { p -> p.isInside(grid) }
                                .filter { grid[it].value() - grid[point].value() <= 1 }
                        }
                    )
                    if (shortestPath.hasPath())
                        paths.add(shortestPath.getScore())
                }
            }
        }
        return paths.min()
    }

    private fun Char.value(): Int = if (this == 'S') 'a'.code else if (this == 'E') 'z'.code else code

    private fun getStartEnd(): Pair<Point2d, Point2d> {
        var start: Point2d? = null
        var end: Point2d? = null
        for (y in grid.heightIndices) {
            for (x in grid.widthIndices) {
                if (grid[x, y] == 'S') {
                    start = Point2d(x, y)
                } else if (grid[x, y] == 'E') {
                    end = Point2d(x, y)
                }
                if (start != null && end != null) return start to end
            }
        }
        throw Error("start-end not found")
    }

}