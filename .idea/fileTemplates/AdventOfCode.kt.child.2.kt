package org.carth.${PackageName}

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Order
import kotlin.test.Test

class ${NAME}Tests : DayTests<${NAME}>(${NAME}::class) {
    @Test
    @Order(10)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 1)

    @Test
    @Order(20)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 1)

    @Test
    @Order(30)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 10)

    @Test
    @Order(40)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 10)
}