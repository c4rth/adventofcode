package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day23Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day23PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample 2`() {
            // Act
            val answer = Day23(testInputAsListOfString("2")).solvePartOne()

            // Assert
            assertEquals(110, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day23(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(4247, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day23PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day23(testInputAsListOfString("2")).solvePartTwo()

            // Assert
            assertEquals(20, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day23(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(1049, answer)
        }
    }
}