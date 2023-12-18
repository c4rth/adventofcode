package org.carth.aoc2023

import org.carth.common.Point2d
import org.carth.common.Puzzle
import org.carth.common.shoelaceArea

class Day18(input: String) : Puzzle<Long, Long>() {

    private val data = input.lines().map { line ->
        line.split(" ").let { (dir, length, color) ->
            Triple(Direction.valueOf(dir), length.toInt(), color.removeSurrounding("(#", ")"))
        }
    }

    override fun solvePartOne(): Long {
        val points = data.map { (dir, length, _) -> dir.point to length }
            .runningFold(Point2d(0, 0)) { point, (dir, length) -> point + dir * length }
        return points.shoelaceArea() + data.sumOf { it.second } / 2 + 1
    }

    override fun solvePartTwo(): Long {
        val dataHex =
            data.map { (_, _, color) ->
                Direction.entries[color.takeLast(1).toInt()].point to color.dropLast(1).toInt(16)
            }
        val points = dataHex.runningFold(Point2d(0, 0)) { point, (dir, length) -> point + dir * length }
        return points.shoelaceArea() + dataHex.sumOf { it.second.toLong() } / 2 + 1
    }

    enum class Direction(val point: Point2d) { R(Point2d.RIGHT), D(Point2d.DOWN), L(Point2d.LEFT), U(Point2d.UP) }

}