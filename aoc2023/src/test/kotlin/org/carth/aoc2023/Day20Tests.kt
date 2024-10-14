package org.carth.aoc2023

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day20Tests : DayTests<Day20>(Day20::class) {
    @Test
    @Order(10)
    fun solvePartOneSample1() = solve(Part.ONE, Type.TEST, "-1", expected = 32000000L)

    @Test
    @Order(11)
    fun solvePartOneSample2() = solve(Part.ONE, Type.TEST, "-2", expected = 11687500L)

    @Test
    @Order(20)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 777666211L)

    @Test
    @Order(40)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 243081086866483L)
}