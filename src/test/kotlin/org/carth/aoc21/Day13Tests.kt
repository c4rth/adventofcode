package org.carth.aoc21

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
            val answer = Day13(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(17, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day13(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(788, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day13PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day13(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(0, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {

            // Act
            val answer = Day13(inputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(0, answer)
        }
    }
}