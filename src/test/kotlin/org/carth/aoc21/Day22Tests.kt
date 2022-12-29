package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day22Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day22PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day22(testInputAsListOfString("1")).solvePartOne()

            // Assert
            assertEquals(590784, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day22(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(655005, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day22PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day22(testInputAsListOfString("2")).solvePartTwo()

            // Assert
            assertEquals(2758514936282235, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day22(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(1125649856443608, answer)
        }
    }
}