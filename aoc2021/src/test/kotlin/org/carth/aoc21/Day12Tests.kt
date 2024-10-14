package org.carth.aoc21

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test


class Day12Tests : DayTests<Day12>(Day12::class) {

    @Test
    @Order(1)
    fun solvePartOneSample1() = solve(Part.ONE, Type.TEST, "a", expected = 10)

    @Test
    @Order(2)
    fun solvePartOneSample2() = solve(Part.ONE, Type.TEST, "b", expected = 19)

    @Test
    @Order(3)
    fun solvePartOneSample3() = solve(Part.ONE, Type.TEST, "c", expected = 226)

    @Test
    @Order(4)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 4749)

    @Test
    @Order(5)
    fun solvePartTwoSample1() = solve(Part.TWO, Type.TEST, "a", expected = 36)

    @Test
    @Order(6)
    fun solvePartTwoSample2() = solve(Part.TWO, Type.TEST, "b", expected = 103)

    @Test
    @Order(7)
    fun solvePartTwoSample3() = solve(Part.TWO, Type.TEST, "c", expected = 3509)

    @Test
    @Order(8)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 123054)

}