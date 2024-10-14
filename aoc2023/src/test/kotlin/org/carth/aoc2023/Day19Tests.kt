package org.carth.aoc2023

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day19Tests : DayTests<Day19>(Day19::class) {
    @Test
    @Order(10)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 19114L)

    @Test
    @Order(20)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 406934L)

    @Test
    @Order(30)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 167409079868000L)

    @Test
    @Order(40)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 131192538505367L)
}