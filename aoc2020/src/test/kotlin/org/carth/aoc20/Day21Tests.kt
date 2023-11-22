package org.carth.aoc20

import org.carth.common.DayTests
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day21Tests : DayTests<Day21>(Day21::class) {
    @Test
    @Order(1)
    fun solvePartOneSample1() = solve(Part.ONE, Type.TEST, expected = 5)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 2659)

    @Test
    @Order(3)
    fun solvePartTwoSample2() = solve(Part.TWO, Type.TEST, expected = "mxmxvkd,sqjhc,fvjkl")

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = "rcqb,cltx,nrl,qjvvcvz,tsqpn,xhnk,tfqsb,zqzmzl")
}