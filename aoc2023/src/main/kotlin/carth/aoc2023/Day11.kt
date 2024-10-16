package org.carth.aoc2023

import org.carth.common.Puzzle
import kotlin.math.abs

class Day11(input: String) : Puzzle<Long, Long>() {

    private val grid = input.lines().map { it.toCharArray().toList() }.toList()

    override fun solvePartOne(): Long {
        val points = getPoints()
        val expandedPoints = expand(points, 2)
        return getSumPathLength(expandedPoints)
    }

    override fun solvePartTwo(): Long {
        val points = getPoints()
        val expandedPoints = expand(points, 1_000_000)
        return getSumPathLength(expandedPoints)
    }

    private fun getPoints(): List<Point2dLong> {
        return grid.flatMapIndexed { y, line ->
            line.mapIndexed { x, c ->
                if (c == '#') Point2dLong(x.toLong(), y.toLong()) else null
            }.filterNotNull()
        }
    }

    private fun getSumPathLength(points: List<Point2dLong>): Long {
        return points.flatMap { point1 ->
            points.map { point2 -> point1.manhattan(point2) }
        }.sum() / 2L
    }

    private fun expand(points: List<Point2dLong>, size: Long): List<Point2dLong> {
        val emptyCols = grid[0].indices.filter { col -> grid.all { it[col] == '.' } }
        val emptyRows = grid.indices.filter { row -> grid[row].all { it == '.' } }
        val incr = size - 1
        return points.map { (x, y) ->
            val deltaX = emptyCols.count { x > it } * incr
            val deltaY = emptyRows.count { y > it } * incr
            Point2dLong(x + deltaX, y + deltaY)
        }
    }
}

data class Point2dLong(val x: Long, val y: Long) {
    fun manhattan(other: Point2dLong) = abs(x - other.x) + abs(y - other.y)
}