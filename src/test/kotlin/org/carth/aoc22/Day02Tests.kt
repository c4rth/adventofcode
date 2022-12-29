package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*

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
            Assertions.assertEquals(15, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day02(inputAsListOfString()).solvePartOne()

            // Assert
            Assertions.assertEquals(11_449, answer)
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
            Assertions.assertEquals(12, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day02(inputAsListOfString()).solvePartTwo()
            // Assert
            Assertions.assertEquals(13_187, answer)
        }
    }
}