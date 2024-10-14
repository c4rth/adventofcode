package org.carth.aoc2023

import org.carth.common.Direction
import org.carth.common.GraphSearchResult
import org.carth.common.GridInt
import org.carth.common.Point2d
import org.carth.common.Puzzle
import org.carth.common.findShortestPathByPredicate
import kotlin.math.min

class Day17(input: String) : Puzzle<Int, Int>() {

    private val grid = GridInt(input.lines().map { line -> line.map { it.digitToInt() }.toIntArray() })

    override fun solvePartOne(): Int {
        val start = Point(Point2d(0, 0), Direction.EAST, 0)
        val end = Point2d(grid.width - 1, grid.height - 1)
        val path = findShortestPathByPredicate(
            start = start,
            endFunction = { point -> point.point == end },
            neighbours = { point -> point.neighbours().filter { neighbour -> neighbour.point.isInside(grid) } },
            cost = { _, to -> grid[to.point]}
        )
        return path.getScore()
    }

    override fun solvePartTwo(): Int {
        val pathA = searchWithStartDirection(Direction.SOUTH).getScore()
        val pathB = searchWithStartDirection(Direction.EAST).getScore()
        return min(pathA, pathB)
    }

    private fun searchWithStartDirection(startDirection: Direction): GraphSearchResult<Point> {
        val start = Point(Point2d(0, 0), startDirection, 0)
        val end = Point2d(grid.width - 1, grid.height - 1)
        return findShortestPathByPredicate(
            start = start,
            endFunction = { point -> point.point == end && point.length >= 4 },
            neighbours = { point -> point.ultraNeighbours().filter { neighbour -> neighbour.point.isInside(grid) } },
            cost = { _, to -> grid[to.point]}
        )
    }

    data class Point(val point: Point2d, val direction: Direction, val length: Int) {

        fun neighbours(): List<Point> {
            return buildList {
                if (length < 3) {
                    add(Point(point + direction.toPoint2d(), direction, length + 1))
                }
                add(Point(point + direction.turnRight().toPoint2d(), direction.turnRight(), 1))
                add(Point(point + direction.turnLeft().toPoint2d(), direction.turnLeft(), 1))
            }
        }

        fun ultraNeighbours(): List<Point> {
            return buildList {
                if (length < 10) {
                    add(Point(point + direction.toPoint2d(), direction, length + 1))
                }
                if (length >= 4) {
                    add(Point(point + direction.turnRight().toPoint2d(), direction.turnRight(), 1))
                    add(Point(point + direction.turnLeft().toPoint2d(), direction.turnLeft(), 1))
                }
            }
        }
    }

}