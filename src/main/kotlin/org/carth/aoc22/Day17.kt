package org.carth.aoc22

import org.carth.common.Point
import org.carth.common.Puzzle
import kotlin.math.absoluteValue

// https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day17.kt
class Day17(data: String) : Puzzle<String, String>() {

    private val jets: List<Point> = parseJets(data)
    private val shapes: List<Set<Point>> = generateShapes()
    private val cave: MutableSet<Point> = (0..6).map { Point(it, 0) }.toMutableSet()
    private val down: Point = Point(0, 1)
    private val up: Point = Point(0, -1)
    private var jetCounter: Int = 0
    private var blockCounter: Int = 0

    override fun solvePartOne(): String {
        repeat(2022) {
            simulate()
        }
        return cave.height().toLong().toString()
    }

    override fun solvePartTwo(): String =
        calculateHeight(1000000000000L - 1).toString()


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

    private operator fun IntRange.contains(set: Set<Point>): Boolean = set.all { it.x in this }
    private operator fun Set<Point>.times(point: Point): Set<Point> = map { it + point }.toSet()
    private fun Set<Point>.minY(): Int = minOf { it.y }
    private fun Set<Point>.height(): Int = minY().absoluteValue

    private fun Set<Point>.normalizedCaveCeiling(): List<Int> =
        groupBy { it.x }
            .entries
            .sortedBy { it.key }
            .map { pointList -> pointList.value.minBy { point -> point.y } }
            .let {
                val normalTo = this.minY()
                it.map { point -> normalTo - point.y }
            }

    private fun Set<Point>.moveToStart(ceilingHeight: Int): Set<Point> =
        map { it + Point(2, ceilingHeight - 4) }.toSet()

    private fun generateShapes(): List<Set<Point>> =
        listOf(
            setOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0)),
            setOf(Point(1, 0), Point(0, -1), Point(1, -1), Point(2, -1), Point(1, -2)),
            setOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(2, -1), Point(2, -2)),
            setOf(Point(0, 0), Point(0, -1), Point(0, -2), Point(0, -3)),
            setOf(Point(0, 0), Point(1, 0), Point(0, -1), Point(1, -1))
        )

    private fun parseJets(input: String): List<Point> =
        input.map {
            when (it) {
                '>' -> Point(1, 0)
                '<' -> Point(-1, 0)
                else -> throw IllegalStateException("No such jet direction $it")
            }
        }

    private fun <T> List<T>.nth(n: Int): T = this[n % size]
}