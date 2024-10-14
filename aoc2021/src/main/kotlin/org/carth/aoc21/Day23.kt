package org.carth.aoc21

import org.carth.common.Puzzle
import java.util.PriorityQueue
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


// Solved by hand
// Solution from https://topaz.github.io/paste/#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

class Day23(input: String) : Puzzle<Long, Long>() {

    private val data = input.lines()

    object Constants {
        val moveCosts = intArrayOf(1, 10, 100, 1000)
    }


    private val rows = data.size - 3

    fun getType(unit: Int): Int = if (unit == -1) -1 else unit / rows

    fun totalUnits(): Int = 4 * rows

    private fun parse(input: List<String>): IntArray {
        val startingPositions = IntArray(totalUnits())
        input.drop(2).dropLast(1).forEachIndexed { i, line ->
            for (j in 0..3) {
                val c = line[2 * j + 3]
                var unit = (c - 'A') * rows
                while (startingPositions[unit] != 0) {
                    unit++
                }
                startingPositions[unit] = 4 * i + j + 7
            }
        }
        return startingPositions
    }

    private fun solvePosition(): Long {
        val startingPositions = parse(data)
        val queue = PriorityQueue<GameState> { obj1, obj2 -> obj1.cost.compareTo(obj2.cost) }
        queue.add(GameState(startingPositions, 0))
        var best = Long.MAX_VALUE
        val alreadyProcessed: MutableMap<String, Long> = HashMap()
        while (queue.isNotEmpty()) {
            val toProcess = queue.poll()
            if (toProcess.cost >= best) {
                break
            }
            for (unit in 0 until totalUnits()) {
                val validPos = findValidPositions(toProcess.positions, unit)
                for (i in validPos.indices) {
                    if (!validPos[i]) {
                        continue
                    }
                    val price = calcPrice(unit, toProcess.positions[unit], i)
                    val next = toProcess.moveUnit(unit, i, price)
                    if (next.isComplete) {
                        best = min(best, next.cost)
                    } else {
                        val repr = next.repr
                        if (next.cost < alreadyProcessed.getOrDefault(repr, Long.MAX_VALUE)) {
                            alreadyProcessed[repr] = next.cost
                            queue.add(next)
                        }
                    }
                }
            }
        }
        return best
    }

    private fun findValidPositions(positions: IntArray, unit: Int): BooleanArray =
        if (positions[unit] < 7) {
            findValidRoomPositions(positions, unit)
        } else {
            findValidHallPositions(positions, unit)
        }

    private fun findValidHallPositions(positions: IntArray, unit: Int): BooleanArray {
        val occupied = IntArray(totalUnits() + 7)
        for (i in 0 until totalUnits() + 7) {
            occupied[i] = -1
        }
        for (i in 0 until totalUnits()) {
            occupied[positions[i]] = i
        }
        val rv = BooleanArray(7)
        val cPos = positions[unit]
        val type = getType(unit)
        run {
            var i = cPos - 4
            while (i > 6) {
                if (occupied[i] > -1) {
                    return rv
                }
                i -= 4
            }
        }
        if ((cPos + 1) % 4 == type) {
            var gottaMove = false
            var i = cPos + 4
            while (i < totalUnits() + 7) {
                if (getType(occupied[i]) != type) {
                    gottaMove = true
                    break
                }
                i += 4
            }
            if (!gottaMove) {
                return rv
            }
        }
        var effPos = cPos
        while (effPos > 10) {
            effPos -= 4
        }
        for (i in 0..6) {
            if (occupied[i] == -1 && checkHallwayClear(i, effPos, occupied)) {
                rv[i] = true
            }
        }
        return rv
    }

    private fun findValidRoomPositions(positions: IntArray, unit: Int): BooleanArray {
        val occupied = IntArray(totalUnits() + 7)
        for (i in 0 until totalUnits() + 7) {
            occupied[i] = -1
        }
        for (i in 0 until totalUnits()) {
            occupied[positions[i]] = i
        }
        val rv = BooleanArray(totalUnits() + 7)
        val cPos = positions[unit]
        val type = getType(unit)
        val room1 = type + 7
        if (!checkHallwayClear(cPos, room1, occupied)) {
            return rv
        }
        var tgt = room1
        for (i in 0 until rows) {
            if (occupied[room1 + 4 * i] == -1) {
                tgt = room1 + 4 * i
            } else if (getType(occupied[room1 + 4 * i]) != type) {
                return rv
            }
        }
        rv[tgt] = true
        return rv
    }

    private fun checkHallwayClear(hallPos: Int, roomPos: Int, occupied: IntArray): Boolean {
        val min = min(hallPos + 1, roomPos - 5)
        val max = max(hallPos - 1, roomPos - 6)
        for (i in min..max) {
            if (occupied[i] != -1) {
                return false
            }
        }
        return true
    }

    private fun calcPrice(unit: Int, from1: Int, to1: Int): Int {
        val from = min(from1, to1)
        val to = max(from1, to1)
        val depth = (to - 3) / 4
        val tgtHall = (to + 1) % 4 * 2 + 3
        val discount = if (from == 0 || from == 6) 1 else 0
        val dist = abs(2 * from - tgtHall) + depth - discount
        val type = getType(unit)
        return Constants.moveCosts[type] * dist
    }

    inner class GameState(val positions: IntArray, val cost: Long) {

        fun moveUnit(unit: Int, position: Int, price: Int): GameState {
            val newPositions: IntArray = positions.copyOf(positions.size)
            newPositions[unit] = position
            return GameState(newPositions, cost + price)
        }

        val isComplete: Boolean
            get() {
                for (i in positions.indices) {
                    val type = getType(i)
                    if (positions[i] < 7 || (positions[i] + 1) % 4 != type) {
                        return false
                    }
                }
                return true
            }

        val repr: String
            get() {
                val occupied = IntArray(totalUnits() + 7)
                for (i in 0 until totalUnits() + 7) {
                    occupied[i] = -1
                }
                for (i in 0 until totalUnits()) {
                    occupied[positions[i]] = i
                }
                var rv = ""
                for (i in 0 until totalUnits() + 7) {
                    val type = getType(occupied[i])
                    if (type == -1) {
                        rv += "x"
                    } else {
                        rv += type
                    }
                }
                return rv
            }
    }

    override fun solvePartOne() = solvePosition()

    override fun solvePartTwo() = solvePosition()
}