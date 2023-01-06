package org.carth.aoc22

import org.carth.common.Point2d
import org.carth.common.Puzzle
import kotlin.math.absoluteValue

// https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day17.kt
class Day17(data: String) : Puzzle<Long, Long>() {

    private val jets: List<Point2d> = parseJets(data)
    private val shapes: List<Set<Point2d>> = generateShapes()
    private val cave: MutableSet<Point2d> = (0..6).map { Point2d(it, 0) }.toMutableSet()
    private val down: Point2d = Point2d(0, 1)
    private val up: Point2d = Point2d(0, -1)
    private var jetCounter: Int = 0
    private var blockCounter: Int = 0

    override fun solvePartOne(): Long {
        repeat(2022) {
            simulate()
        }
        return cave.height().toLong()
    }

    override fun solvePartTwo(): Long =
        calculateHeight(1000000000000L - 1)


    private fun simulate() {
        var thisShape = shapes.nth(blockCounter++).moveToStart(cave.minY())
        do {
            val jettedShape = thisShape * jets.nth(jetCounter++)
            if (jettedShape in (0..6) && jettedShape.intersect(cave).isEmpty()) {
                thisShape = jettedShape
            }
            thisShape = thisShape * down
        } while (thisShape.intersect(cave).isEmpty())
        cave += (thisShape * up)
    }

    private fun calculateHeight(targetBlockCount: Long): Long {
        data class State(val ceiling: List<Int>, val blockMod: Int, val jetMod: Int)

        val seen: MutableMap<State, Pair<Int, Int>> = mutableMapOf()
        while (true) {
            simulate()
            val state = State(cave.normalizedCaveCeiling(), blockCounter % shapes.size, jetCounter % jets.size)
            if (state in seen) {
                // Fast forward
                val (blockCountAtLoopStart, heightAtLoopStart) = seen.getValue(state)
                val blocksPerLoop: Long = blockCounter - 1L - blockCountAtLoopStart
                val totalLoops: Long = (targetBlockCount - blockCountAtLoopStart) / blocksPerLoop
                val remainingBlocksFromClosestLoopToGoal: Long =
                    (targetBlockCount - blockCountAtLoopStart) - (totalLoops * blocksPerLoop)
                val heightGainedSinceLoop = cave.height() - heightAtLoopStart
                repeat(remainingBlocksFromClosestLoopToGoal.toInt()) {
                    simulate()
                }
                return cave.height() + (heightGainedSinceLoop * (totalLoops - 1))
            }
            seen[state] = blockCounter - 1 to cave.height()
        }
    }

    private operator fun IntRange.contains(set: Set<Point2d>): Boolean = set.all { it.x in this }
    private operator fun Set<Point2d>.times(point2d: Point2d): Set<Point2d> = map { it + point2d }.toSet()
    private fun Set<Point2d>.minY(): Int = minOf { it.y }
    private fun Set<Point2d>.height(): Int = minY().absoluteValue

    private fun Set<Point2d>.normalizedCaveCeiling(): List<Int> =
        groupBy { it.x }
            .entries
            .sortedBy { it.key }
            .map { pointList -> pointList.value.minBy { point -> point.y } }
            .let {
                val normalTo = this.minY()
                it.map { point -> normalTo - point.y }
            }

    private fun Set<Point2d>.moveToStart(ceilingHeight: Int): Set<Point2d> =
        map { it + Point2d(2, ceilingHeight - 4) }.toSet()

    private fun generateShapes(): List<Set<Point2d>> =
        listOf(
            setOf(Point2d(0, 0), Point2d(1, 0), Point2d(2, 0), Point2d(3, 0)),
            setOf(Point2d(1, 0), Point2d(0, -1), Point2d(1, -1), Point2d(2, -1), Point2d(1, -2)),
            setOf(Point2d(0, 0), Point2d(1, 0), Point2d(2, 0), Point2d(2, -1), Point2d(2, -2)),
            setOf(Point2d(0, 0), Point2d(0, -1), Point2d(0, -2), Point2d(0, -3)),
            setOf(Point2d(0, 0), Point2d(1, 0), Point2d(0, -1), Point2d(1, -1))
        )

    private fun parseJets(input: String): List<Point2d> =
        input.map {
            when (it) {
                '>' -> Point2d(1, 0)
                '<' -> Point2d(-1, 0)
                else -> throw IllegalStateException("No such jet direction $it")
            }
        }

    private fun <T> List<T>.nth(n: Int): T = this[n % size]
}