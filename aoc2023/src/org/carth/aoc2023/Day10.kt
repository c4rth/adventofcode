package org.carth.aoc2023

import org.carth.common.Point2d
import org.carth.common.Puzzle
import kotlin.math.abs

class Day10(private val input: String) : Puzzle<Int, Int>() {

    private val pipes = mapOf(
        '|' to listOf(Point2d.S, Point2d.N),
        'L' to listOf(Point2d.E, Point2d.N),
        'J' to listOf(Point2d.N, Point2d.W),
        '7' to listOf(Point2d.S, Point2d.W),
        'F' to listOf(Point2d.E, Point2d.S),
        '-' to listOf(Point2d.E, Point2d.W),
        'S' to listOf(Point2d.S, Point2d.E, Point2d.N, Point2d.W),
        '.' to emptyList()
    )

    private fun getPoints(): List<Point2d> {
        var pointS: Point2d? = null
        val map = input.lines().flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, c ->
                if (c == 'S') pointS = Point2d(x, y)
                Point2d(x, y) to pipes.getValue(c).map { p2 -> Point2d(x, y) + p2 }
            }
        }.toMap()
        val start = pointS!!
        val firstMove = map.getValue(start).first { from -> map.getValue(from).any { it == start } }
        return generateSequence(start to firstMove) { (from, to) ->
            when (to) {
                start -> null
                else -> to to map.getValue(to).minus(from).first()
            }
        }.map { it.first }.toList()
    }

    override fun solvePartOne(): Int {
        val points = getPoints()
        return points.size / 2
    }

    private fun shoelaceArea(points: List<Point2d>): Int {
        val length = points.size
        var acc = 0
        for (i in 0 until length - 1) {
            acc += points[i].x * points[i + 1].y - points[i + 1].x * points[i].y
        }
        return abs(acc + points[length - 1].x * points[0].y - points[0].x * points[length - 1].y) / 2
    }

    override fun solvePartTwo(): Int {
        val points = getPoints()
        return shoelaceArea(points) - (points.size / 2) + 1
    }

}