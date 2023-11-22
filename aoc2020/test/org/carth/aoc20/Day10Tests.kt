package org.carth.aoc20

import org.carth.common.DayTests
import org.junit.jupiter.api.Order
import kotlin.test.Test

class Day10Tests : DayTests<Day10>(Day10::class) {
    @Test
    @Order(1)
    fun solvePartOneSample1() = solve(Part.ONE, Type.TEST, "1", expected = 35)
    @Test
    @Order(2)
    fun solvePartOneSample2() = solve(Part.ONE, Type.TEST, "2",expected = 220)

    @Test
    @Order(3)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 2432)

    @Test
    @Order(4)
    fun solvePartTwoSample1() = solve(Part.TWO, Type.TEST, "1", expected = 8L)

    @Test
    @Order(5)
    fun solvePartTwoSample2() = solve(Part.TWO, Type.TEST, "2", expected = 19208L)

    @Test
    @Order(6)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 453551299002368L)
}