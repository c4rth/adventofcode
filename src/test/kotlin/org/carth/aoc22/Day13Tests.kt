package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day13Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day13PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {

            // Act
            val answer = Day13(testInputAsText()).solvePartOne()

            // Assert
            assertEquals(13, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day13(inputAsText()).solvePartOne()

            // Assert
            assertEquals(6076, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day13PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day13(testInputAsText()).solvePartTwo()

            // Assert
            assertEquals(140, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {

            // Act
            val answer = Day13(inputAsText()).solvePartTwo()

            // Assert
            assertEquals(24805, answer)
        }
    }
}