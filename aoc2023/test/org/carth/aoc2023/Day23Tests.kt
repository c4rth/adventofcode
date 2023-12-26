package org.carth.aoc2023

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Order
import kotlin.test.Test

class Day23Tests : DayTests<Day23>(Day23::class) {
    @Test
    @Order(10)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 94)

    @Test
    @Order(20)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 2294)

    @Test
    @Order(30)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 154)

    @Test
    @Order(40)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 6418)
}