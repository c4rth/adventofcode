package org.carth.aoc22

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
            val answer = Day04(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(2, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day04(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(560, answer)
        }
    }

    @Nested
    @Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day04PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day04(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(4, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day04(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(839, answer)
        }
    }
}