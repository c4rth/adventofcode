package org.carth.aoc21

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
            assertEquals(4512, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day04(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(6592, answer)
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
            assertEquals(1924, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day04(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(31755, answer)
        }
    }
}