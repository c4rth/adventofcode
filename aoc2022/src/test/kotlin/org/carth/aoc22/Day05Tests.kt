package org.carth.aoc22

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test


class Day05Tests : DayTests<Day05>(Day05::class) {
    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = "CMZ")

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = "QGTHFZBHV")

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = "MCD")

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = "MGDMPSZTM")
}