package org.carth.aoc22

import org.carth.common.Point3d
import org.carth.common.Puzzle
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.component3


class Day18(input: String) : Puzzle<Int, Int>() {

    private val cubes =
        input.lines().map { line ->
            line.split(",").map { str ->
                str.toInt()
            }.let { (x, y, z) ->
                Point3d(x, y, z)
            }
        }.toSet()
    private val xRange = cubes.minOf { it.x } until cubes.maxOf { it.x }
    private val yRange = cubes.minOf { it.y } until cubes.maxOf { it.y }
    private val zRange = cubes.minOf { it.z } until cubes.maxOf { it.z }

    private val allPoint3ds = xRange.flatMap { x -> yRange.flatMap { y -> zRange.map { z -> Point3d(x, y, z) } } }
    private val trapped = allPoint3ds.toMutableSet()

    private fun removeTrapped(start: Point3d, cur: Point3d = start, seen: Set<Point3d> = setOf(start)) {
        if (cur.x !in xRange || cur.y !in yRange || cur.z !in zRange) {
            trapped.removeAll(seen + cur)
        } else {
            cur.directAdjacents()
                .filter { it !in cubes && it !in seen }
                .forEach { return removeTrapped(start, it, seen + cur) }
        }
    }

    override fun solvePartOne(): Int {
        return cubes.sumOf { cube -> cube.directAdjacents().count { adjacentCube -> adjacentCube !in cubes } }
    }

    override fun solvePartTwo(): Int {
        allPoint3ds.forEach { removeTrapped(it) }
        return cubes.sumOf { cube -> cube.directAdjacents().count { it !in cubes && it !in trapped } }

    }

}

