package org.carth.aoc2023

import org.carth.common.Point3d
import org.carth.common.Puzzle

class Day22(input: String) : Puzzle<Int, Int>() {

    private val bricksPoints = input.lines().map { line ->
        line.split("~").let { (from, to) ->
            Point3d(from.split(",")) to Point3d(to.split(","))
        }
    }.map { (brick1, brick2) ->
        val points = hashSetOf<Point3d>()
        for (x in brick1.x..brick2.x) {
            for (y in brick1.y..brick2.y) {
                for (z in brick1.z..brick2.z) {
                    points += Point3d(x, y, z)
                }
            }
        }
        points
    }.let { bricks -> bricks.sortedBy { brick -> brick.minOf { point -> point.z } } }

    private fun fall(bricks: List<Set<Point3d>>): Pair<List<Set<Point3d>>, Int> {
        val new = mutableListOf<Set<Point3d>>()
        val fallen = hashSetOf<Point3d>()
        var a = 0
        for (brick in bricks) {
            var cb = brick
            while (true) {
                val brickDown = cb.mapTo(hashSetOf()) { point -> point - Point3d(0, 0, 1) }
                if (brickDown.any { point -> point in fallen || point.z <= 0 }) {
                    new += cb
                    fallen += cb
                    if (cb != brick) a++
                    break
                }
                cb = brickDown
            }
        }
        return new to a
    }

    override fun solvePartOne(): Int {
        val (fallen, _) = fall(bricksPoints)
        val total = fallen.map { brick ->
            fall(fallen.minusElement(brick)).second
        }
        return total.count { it == 0 }
    }

    override fun solvePartTwo(): Int {
        val (fallen, _) = fall(bricksPoints)
        val total = fallen.map { brick ->
            fall(fallen.minusElement(brick)).second
        }
        return total.sum()
    }

}