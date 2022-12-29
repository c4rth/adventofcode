package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day21Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day21PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day21(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(739_785, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day21(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(598_416, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day21PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day21(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(444_356_092_776_315, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day21(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(27_674_034_218_179, answer)
        }
    }
}