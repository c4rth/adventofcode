package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day03Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day03PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {

            // Act
            val answer = Day03(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(157, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day03(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(8493, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day03PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {

            // Act
            val answer = Day03(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(70, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day03(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(2552, answer)
        }

    }
}