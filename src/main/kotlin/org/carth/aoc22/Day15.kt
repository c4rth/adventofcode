package org.carth.aoc22

import org.carth.common.Point
import org.carth.common.Puzzle
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day15(input: String) : Puzzle<Int, Int>() {
    private val data = input.lines()
    private val sensors = parse()
    override fun solvePartOne(): Int = 0

    fun solvePartOne(row: Int): Int {
        val notInRange = findNotInRange(row)
        val uniqueNotInRange = notInRange.flatten().distinct()
        val beaconsInRow = sensors.map { it.second }.filter { it.y == row }.distinct()
        return (uniqueNotInRange.size - beaconsInRow.size)
    }

    override fun solvePartTwo(): Int = 0

    fun solvePartTwo(max: Int): Long {
        for (y in 0..max) {
            val x = findOpenSpot(y)
            if (x != -1L) {
                return (x * 4_000_000 + y)
            }
        }
        return 0
    }

    private fun parse(): List<Triple<Point, Point, Int>> {
        return data.map { line ->
            line.split("Sensor at x=", ", y=", ": closest beacon is at x=", ", y=")
                .drop(1)
                .map { it.toInt() }
        }.map { (sx, sy, bx, by) ->
            val sensor = Point(sx, sy)
            val beacon = Point(bx, by)
            Triple(sensor, beacon, sensor.manhattan(beacon))
        }
    }

    private fun findNotInRange(i: Int): Set<IntRange> {
        val notInRanges = mutableSetOf<IntRange>()
        sensors.forEach { (sensor, _, distance) ->
            val dSToI = i - sensor.y
            if (abs(dSToI) <= distance) {
                val dx = distance - abs(dSToI)
                notInRanges.add(-dx + sensor.x..dx + sensor.x)
            }
        }
        return notInRanges
    }

    private fun findOpenSpot(i: Int): Long {
        val notInRanges = findNotInRange(i)
        val rangeList = notInRanges.sortedBy { it.first }.toList()
        var bigRange = rangeList[0]
        rangeList.forEach { range ->
            if (!bigRange.contains(range)) {
                if (bigRange.overlaps(range) || bigRange.last + 1 == range.first) {
                    bigRange = bigRange.merge(range)
                } else {
                    return bigRange.last + 1.toLong()
                }
            }
        }

        return -1
    }

    private fun IntRange.contains(b: IntRange): Boolean =
        this.first <= b.first && b.last <= this.last

    private fun IntRange.overlaps(b: IntRange): Boolean =
        this.contains(b.first) || this.contains(b.last) || b.contains(this.first)

    private fun IntRange.merge(b: IntRange): IntRange = min(this.first, b.first)..max(this.last, b.last)


}