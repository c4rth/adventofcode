package org.carth.${DirName}

import org.carth.common.DayTests
import org.junit.jupiter.api.Order
import kotlin.test.Test

class ${NAME}Tests : DayTests<${NAME}>(${NAME}::class) {
    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 1)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 1)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 10)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 10)
}