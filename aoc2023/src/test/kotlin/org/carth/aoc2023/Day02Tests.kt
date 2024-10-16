package org.carth.aoc2023

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day02Tests : DayTests<Day02>(Day02::class) {
    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 8)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 2369)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 2286)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 66363)
}