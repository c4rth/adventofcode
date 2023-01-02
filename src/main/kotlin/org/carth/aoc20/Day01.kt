package org.carth.aoc20

import org.carth.common.Puzzle

class Day01(input: String) : Puzzle<String, String>() {

    private val data = input.lines().map { it.toInt() }

    override fun solvePartOne(): String {
        var first = 0
        var second = 0
        for (x in data) {
            second = data.firstOrNull { x + it == 2020 } ?: 0
            if (second != 0) {
                first = x
                break
            }
        }
        return (first * second).toString()
    }

    override fun solvePartTwo(): String {
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
        return (first * second * third).toString()
    }
}