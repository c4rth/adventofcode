package org.carth.aoc2023

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day12Tests : DayTests<Day12>(Day12::class) {
    @Test
    @Order(10)
    fun solvePartOneSample1() = solve(Part.ONE, Type.TEST, "-1", expected = 6L)

    @Test
    @Order(11)
    fun solvePartOneSample2() = solve(Part.ONE, Type.TEST, "-2", expected = 21L)

    @Test
    @Order(20)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 7361L)

    @Test
    @Order(30)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, "-2", expected = 525152L)

    @Test
    @Order(40)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 83317216247365L)
}