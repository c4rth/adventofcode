package org.carth.aoc2023

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Order
import kotlin.test.Test

class Day08Tests : DayTests<Day08>(Day08::class) {
    @Test
    @Order(1)
    fun solvePartOneSample1() = solve(Part.ONE, Type.TEST, "-1", expected = 2)

    @Test
    @Order(2)
    fun solvePartOneSample2() = solve(Part.ONE, Type.TEST, "-2", expected = 6)

    @Test
    @Order(3)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 18827)

    @Test
    @Order(4)
    fun solvePartTwoSample1() = solve(Part.TWO, Type.TEST, "-3", expected = 6L)

    @Test
    @Order(5)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 20220305520997L)
}