package org.carth.aoc20

import org.carth.common.Puzzle

class Day01(private val data: List<Int>) : Puzzle<Int, Int>() {

    override fun solvePartOne(): Int {
        var first = 0
        var second = 0
        for (x in data) {
            second = data.firstOrNull { x + it == 2020 } ?: 0
            if (second != 0) {
                first = x
                break
            }
        }
        return first * second
    }

    override fun solvePartTwo(): Int {
        var first = 0
        var second = 0
        var third = 0
        loop@ for (x in data) {
            for (y in data) {
                third = data.firstOrNull { z ->
                    x + y + z == 2020
                } ?: 0
                if (third != 0) {
                    first = x
                    second = y
                    break@loop
                }
            }
        }
        return first * second * third
    }
}