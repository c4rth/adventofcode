package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day06Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day06PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day06(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(5934, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day06(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(396210, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day06PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day06(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(26984457539, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day06(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(1770823541496, answer)
        }
    }
 }