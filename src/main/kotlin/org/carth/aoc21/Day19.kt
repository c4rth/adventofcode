package org.carth.aoc21

import org.carth.common.Puzzle
import kotlin.math.abs

// https://github.com/ClouddJR/AdventOfCode2021/blob/master/src/main/kotlin/com/clouddjr/advent2021/Day19.kt

class Day19(input: String) : Puzzle<String, String>() {

    private val data = input.lines()
    private val scanners = parse(data)

    override fun solvePartOne() = assembleMap().beacons.size.toString()

    override fun solvePartTwo() = assembleMap().scannersPositions.let { positions ->
        positions.flatMapIndexed { index, first -> positions.drop(index + 1).map { second -> first to second } }
            .maxOf { (first, second) -> first distanceTo second }
            .toString()
    }

    private fun parse(input: List<String>): List<Scanner> {
        val scanners = mutableListOf<Scanner>()
        var index = 1
        while (index < input.size - 1) {
            val points = mutableSetOf<Point3D>()
            while (index < input.size - 1 && input[index].isNotEmpty()) {
                val (x, y, z) = input[index].split(",").map { it.toInt() }
                points.add(Point3D(x, y, z))
                index++
            }
            scanners.add(Scanner(points))
            index += 2
        }
        return scanners
    }

    private fun assembleMap(): AssembledMap {
        val foundBeacons = scanners.first().beacons.toMutableSet()
        val foundScannersPositions = mutableSetOf(Point3D(0, 0, 0))

        val remaining = ArrayDeque<Scanner>().apply { addAll(scanners.drop(1)) }
        while (remaining.isNotEmpty()) {
            val candidate = remaining.removeFirst()
            when (val transformedCandidate = Scanner(foundBeacons).getTransformedIfOverlap(candidate)) {
                null -> remaining.add(candidate)
                else -> {
                    foundBeacons.addAll(transformedCandidate.beacons)
                    foundScannersPositions.add(transformedCandidate.position)
                }
            }
        }

        return AssembledMap(foundBeacons, foundScannersPositions)
    }

    private data class Scanner(val beacons: Set<Point3D>) {
        fun allRotations() = beacons.map { it.allRotations() }.transpose().map { Scanner(it) }

        fun getTransformedIfOverlap(otherScanner: Scanner): TransformedScanner? {
            return otherScanner.allRotations().firstNotNullOfOrNull { otherReoriented ->
                beacons.firstNotNullOfOrNull { first ->
                    otherReoriented.beacons.firstNotNullOfOrNull { second ->
                        val otherPosition = first - second
                        val otherTransformed = otherReoriented.beacons.map { otherPosition + it }.toSet()
                        when ((otherTransformed intersect beacons).size >= 12) {
                            true -> TransformedScanner(otherTransformed, otherPosition)
                            false -> null
                        }
                    }
                }
            }
        }

        private fun List<Set<Point3D>>.transpose(): List<Set<Point3D>> {
            return when (all { it.isNotEmpty() }) {
                true -> listOf(map { it.first() }.toSet()) + map { it.drop(1).toSet() }.transpose()
                false -> emptyList()
            }
        }
    }

    private data class TransformedScanner(val beacons: Set<Point3D>, val position: Point3D)

    private data class AssembledMap(val beacons: Set<Point3D>, val scannersPositions: Set<Point3D>)

    private data class Point3D(val x: Int, val y: Int, val z: Int) {
        operator fun plus(other: Point3D) = Point3D(x + other.x, y + other.y, z + other.z)

        operator fun minus(other: Point3D) = Point3D(x - other.x, y - other.y, z - other.z)

        infix fun distanceTo(other: Point3D) = abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

        fun allRotations(): Set<Point3D> {
            return setOf(
                Point3D(x, y, z), Point3D(x, -z, y), Point3D(x, -y, -z), Point3D(x, z, -y), Point3D(-x, -y, z),
                Point3D(-x, -z, -y), Point3D(-x, y, -z), Point3D(-x, z, y), Point3D(-z, x, -y), Point3D(y, x, -z),
                Point3D(z, x, y), Point3D(-y, x, z), Point3D(z, -x, -y), Point3D(y, -x, z), Point3D(-z, -x, y),
                Point3D(-y, -x, -z), Point3D(-y, -z, x), Point3D(z, -y, x), Point3D(y, z, x), Point3D(-z, y, x),
                Point3D(z, y, -x), Point3D(-y, z, -x), Point3D(-z, -y, -x), Point3D(y, -z, -x),
            )
        }
    }
}