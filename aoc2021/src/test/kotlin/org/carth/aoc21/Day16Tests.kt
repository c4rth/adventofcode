package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test


class Day16Tests : DayTests<Day16>(Day16::class) {

    @Test
    @Order(1)
    fun solvePartOneSample1a() = solve(Part.ONE, Type.TEST, "1a", expected = 6)

    @Test
    @Order(2)
    fun solvePartOneSample1b() = solve(Part.ONE, Type.TEST, "1b", expected = 16)

    @Test
    @Order(3)
    fun solvePartOneSample1c() = solve(Part.ONE, Type.TEST, "1c", expected = 12)

    @Test
    @Order(4)
    fun solvePartOneSample1d() = solve(Part.ONE, Type.TEST, "1d", expected = 23)

    @Test
    @Order(5)
    fun solvePartOneSample1e() = solve(Part.ONE, Type.TEST, "1e", expected = 31)

    @Test
    @Order(6)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 991)

    @Test
    @Order(7)
    fun solvePartTwoSample2a() = solve(Part.TWO, Type.TEST, "2a", expected = 3L)

    @Test
    @Order(8)
    fun solvePartTwoSample2b() = solve(Part.TWO, Type.TEST, "2b", expected = 54L)

    @Test
    @Order(9)
    fun solvePartTwoSample2c() = solve(Part.TWO, Type.TEST, "2c", expected = 7L)

    @Test
    @Order(10)
    fun solvePartTwoSample2d() = solve(Part.TWO, Type.TEST, "2d", expected = 9L)

    @Test
    @Order(11)
    fun solvePartTwoSample2e() = solve(Part.TWO, Type.TEST, "2e", expected = 1L)

    @Test
    @Order(12)
    fun solvePartTwoSample2f() = solve(Part.TWO, Type.TEST, "2f", expected = 0L)

    @Test
    @Order(13)
    fun solvePartTwoSample2g() = solve(Part.TWO, Type.TEST, "2g", expected = 0L)

    @Test
    @Order(14)
    fun solvePartTwoSample2h() = solve(Part.TWO, Type.TEST, "2h", expected = 1L)

    @Test
    @Order(15)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 1264485568252L)
}