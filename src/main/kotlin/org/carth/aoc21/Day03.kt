package org.carth.aoc21

import org.carth.common.Puzzle

class Day03(private val data: List<String>) : Puzzle<Int, Int>() {

    override fun solvePartOne(): Int {
        val length = data.first().length - 1
        val counterOf1 = IntArray(length + 1) { 0 }
        data.forEach { line ->
            line.forEachIndexed { index, c ->
                if (c == '1') counterOf1[index] += 1
            }
        }
        val gamma = StringBuilder()
        val epsilon = StringBuilder()
        val map = mapOf(true to "1", false to "0")
        counterOf1.forEach { counter ->
            (counter >= data.size - counter).let { test ->
                gamma.append(map[test])
                epsilon.append(map[!test])
            }
        }
        return gamma.toString().toInt(2) * epsilon.toString().toInt(2)
    }

    private fun getCounters(index: Int, data: List<String>): Pair<Int, Int> {
        var counterOf1 = 0
        var counterOf0 = 0
        data.forEach { line ->
            if (line[index] == '1') counterOf1 += 1 else counterOf0 += 1
        }
        return Pair(counterOf0, counterOf1)
    }

    private fun getGas(data: List<String>, test: (Int, Int) -> Char): String {
        var index = 0
        var input = data
        while (input.size > 1) {
            val mostFound = getCounters(index, input).let { (counterOf0, counterOf1) ->
                test(counterOf0, counterOf1)
            }
            input = input.filter { line ->
                line[index] == mostFound
            }
            index++
        }
        return input.first()
    }

    override fun solvePartTwo(): Int {

        val oxygen = getGas(data) { counterOf0, counterOf1 ->
            if (counterOf0 > counterOf1) '0' else '1'
        }

        val co2 = getGas(data) { counterOf0, counterOf1 ->
            if (counterOf0 <= counterOf1) '0' else '1'
        }

        return oxygen.toInt(2) * co2.toInt(2)
    }
}