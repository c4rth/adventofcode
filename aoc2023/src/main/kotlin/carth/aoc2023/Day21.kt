package org.carth.aoc2023

import org.carth.common.GridChar
import org.carth.common.GridInt
import org.carth.common.Point2d
import org.carth.common.Puzzle
import java.util.*
import kotlin.math.max


class Day21(input: String) : Puzzle<Long, Long>() {

    private val grid = GridChar(input)

    private val start = grid['S']!!

    override fun solvePartOne(): Long {
        var points = mutableSetOf(start)
        repeat(64) {
            val newPoints = mutableSetOf<Point2d>()
            for (point in points) {
                newPoints.addAll(grid.cardinalAdjacent(point).filter { p -> grid[p] != '#' })
            }
            points = newPoints
        }
        return points.size.toLong()
    }

    private val size = grid.width

    private fun makeBlock(initial: Iterable<Pair<Int, Point2d>>): GridInt {
        val queue = PriorityQueue<Pair<Int, Point2d>>(compareBy { it.first })
        val block = GridInt(size, size) { -1 }
        queue.addAll(initial)
        while (!queue.isEmpty()) {
            val (step, point) = queue.remove()
            val existing = block[point]
            if (existing in 0..step) continue
            block[point] = step
            for (next in grid.cardinalAdjacent(point).filter { grid[it] != '#' }) {
                val candidate = block[next]
                if (candidate < 0) {
                    queue.add(Pair(step + 1, next))
                }
            }
        }
        return block
    }

    override fun solvePartTwo(): Long {
        val numSteps = args[0] as Int
        val origin = makeBlock(listOf(0 to start))
        var acc = origin.count { it in 0..numSteps && it xor numSteps and 1 == 0 }.toLong()
        for (quadrant in 0..<4) {
            val signY = quadrant and 1 == 0
            val signX = quadrant and 2 == 0
            val block = makeBlock(
                listOf(
                    origin[if (signX) 0 else size - 1, if (signY) 0 else size - 1] + 2 to
                            Point2d(if (signX) size - 1 else 0, if (signY) size - 1 else 0)
                )
            )
            acc += block.sumOf { step ->
                if (step !in 0..numSteps) return@sumOf 0L
                val remaining = numSteps - step
                if (remaining % 2 == 0L) {
                    (remaining / size / 2 + 1).let { it * it }.toLong()
                } else {
                    ((remaining / size + 1) / 2).let { it * (it + 1) }.toLong()
                }
            }
        }

        for (axis in 0..<4) {
            val sign = axis and 1 == 0
            val src = if (sign) 0 else size - 1
            val dst = if (sign) size - 1 else 0
            val orientation = axis and 2 == 0
            var block = origin
            do {
                val lastBlock = block
                block = makeBlock(
                    (0..<size).map {
                        lastBlock[if (orientation) src else it, if (orientation) it else src] + 1 to
                                Point2d(if (orientation) dst else it, if (orientation) it else dst)
                    }
                )
                acc += block.count { it in 0..numSteps && it xor numSteps and 1 == 0 }
            } while (
                block.any { it in 0..numSteps } &&
                block.withIndex().any { (i, step) -> step >= 0 && step - lastBlock[i] != size }
            )
            acc += block.sumOf { step ->
                if (step >= 0) {
                    val remaining = numSteps - step + size
                    max((remaining + 1) / size - remaining.and(1), 0) / 2
                } else 0L
            }
        }
        return acc
    }
}

