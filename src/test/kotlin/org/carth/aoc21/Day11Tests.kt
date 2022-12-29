package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day11Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day11PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {

            // Act
            val answer = Day11(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(1656, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day11(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(1627, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day11PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day11(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(195, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day11(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(329, answer)
        }
    }
}