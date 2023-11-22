package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.Order
import kotlin.test.Test

class Day09Tests : DayTests<Day09>(Day09::class) {

    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, "1", expected = 13)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 6026)

    @Test
    @Order(3)
    fun solvePartTwoSample1() = solve(Part.TWO, Type.TEST, "1", expected = 1)

    @Test
    @Order(4)
    fun solvePartTwoSample2() = solve(Part.TWO, Type.TEST, "2", expected = 36)

    @Test
    @Order(5)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 2273)
}