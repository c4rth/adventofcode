package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class Day18Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day18PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample 1`() {
            // Act
            val answer = Day18(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(64, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day18(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(4348, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day18PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day18(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(58, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day18(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(2546, answer)
        }
    }
}