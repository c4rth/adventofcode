package org.carth.aoc21

import org.carth.common.Puzzle
import kotlin.math.abs

class Day07(input: String) : Puzzle<String, String>() {

    private val data = input.lines()

    private inline fun calcSubTotal(input: List<Int>, calc: (Int) -> Int): Int {
        var subTotal = 0
        input.forEach {
            subTotal += calc(it)
        }
        return subTotal
    }

    private inline fun calcMinFuel(calcDelta: (Int, Int) -> Int): Int {
        val input = data.first().split(",").map { it.toInt() }
        var minFuel = Int.MAX_VALUE
        for (position in 0..input.size) {
            val subTotal = calcSubTotal(input) {
                calcDelta(it, position)
            }
            if (subTotal < minFuel) {
                minFuel = subTotal
            }
        }
        return minFuel
    }

    override fun solvePartOne(): String {
        return calcMinFuel { x, position ->
            abs(x - position)
        }.toString()
    }

    override fun solvePartTwo(): String {
        return calcMinFuel { x, position ->
            val delta = abs(x - position)
            (delta * delta + delta) / 2
        }.toString()
    }
}