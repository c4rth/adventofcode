package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day01Tests : DayTests<Day01>(Day01::class) {
    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 24000)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 67633)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 45000)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 199628)
}