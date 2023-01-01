package org.carth.aoc20

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class Day04Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day04PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {

            // Act
            val answer = Day04(testInputAsText()).solvePartOne()

            // Assert
            assertEquals(2, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day04(inputAsText()).solvePartOne()

            // Assert
            assertEquals(245, answer)
        }
    }

    @Nested
    @Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day04PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample 1`() {
            // Act
            val answer = Day04(testInputAsText()).solvePartTwo()

            // Assert
            assertEquals(2, answer)
        }
        @Test
        @Order(1)
        fun `solve part two of sample 2`() {
            // Act
            val answer = Day04(testInputAsText("1")).solvePartTwo()

            // Assert
            assertEquals(4, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day04(inputAsText()).solvePartTwo()
            // Assert
            assertEquals(133, answer)
        }
    }
}