package org.carth.aoc20

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day02Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day02PartOneTest {

        @Test
        @Order(1)
        fun `solve part one sample`() {
            // Act
            val answer = Day02(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(2, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day02(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(393, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day02PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two sample`() {
            // Act
            val answer = Day02(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(1, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day02(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(690, answer)
        }
    }
}