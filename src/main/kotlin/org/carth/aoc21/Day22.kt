package org.carth.aoc21

import org.carth.common.Puzzle
import kotlin.math.min
import kotlin.math.max

class Day22(private val data: List<String>) : Puzzle<Long, Long>() {
    override fun solvePartOne(): Long {
        val limit = Cube(Point3d(-50, -50, -50), Point3d(50, 50, 50))
        val steps = parseData(data).filter { it.cube.intersect(limit) != null }
        return markAllCubes(steps)
    }

    override fun solvePartTwo(): Long {
        val steps = parseData(data)
        return markAllCubes(steps)
    }

    private fun parseData(input: List<String>): List<Step> {
        val steps = mutableListOf<Step>()
        val regex = "\\w+ x=(-?\\d+)\\.\\.(-?\\d+),y=(-?\\d+)\\.\\.(-?\\d+),z=(-?\\d+)\\.\\.(-?\\d+)".toRegex()
        input.forEach { line ->
            val coord = regex.matchEntire(line)!!.destructured.toList().map { it.toLong() }.toList()
            steps.add(Step(line.startsWith("on"), Cube(Point3d(coord[0], coord[2], coord[4]), Point3d(coord[1], coord[3], coord[5]))))
        }
        return steps
    }

    private fun markAllCubes(steps: List<Step>): Long {
        val counts = mutableMapOf<Cube, Long>().withDefault { 0L }
        val updatedCounts = mutableMapOf<Cube, Long>().withDefault { 0L }
        steps.forEach { step ->
            counts.forEach { (cube, count) ->
                step.cube.intersect(cube)?.let { intersect ->
                    updatedCounts[intersect] = updatedCounts.getValue(intersect) - count
                }
            }
            if (step.on) {
                updatedCounts[step.cube] = updatedCounts.getValue(step.cube) + 1
            }
            updatedCounts.forEach { (cube, count) ->
                counts.merge(cube, count) { old, new -> old + new }
            }
            updatedCounts.clear()
        }
        return counts.toList().fold(0L) { acc, (cube, count) ->  acc + cube.volume * count}
    }

}

private data class Step(val on: Boolean, val cube: Cube)

private data class Cube(val from: Point3d, val to: Point3d) {

    val volume = ((to.x - from.x + 1) * (to.y - from.y + 1) * (to.z - from.z + 1))

    fun intersect(other: Cube): Cube? {
        val pFrom = Point3d(max(this.from.x, other.from.x), max(this.from.y, other.from.y), max(this.from.z, other.from.z))
        val pTo = Point3d(min(this.to.x, other.to.x), min(this.to.y, other.to.y), min(this.to.z, other.to.z))
        return if (pFrom <= pTo) Cube(pFrom, pTo) else null
    }
}

private data class Point3d(val x: Long, val y: Long, val z: Long) : Comparable<Point3d> {

    override fun compareTo(other: Point3d): Int =
        if (this.x <= other.x && this.y <= other.y && this.z <= other.z) -1 else 1

}
