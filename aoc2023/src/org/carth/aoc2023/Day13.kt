package org.carth.aoc2023

import org.carth.common.GridString
import org.carth.common.Puzzle

class Day13(input: String) : Puzzle<Int, Int>() {

    private val patterns = input.split(System.lineSeparator() + System.lineSeparator()).map { GridString(it) }

    override fun solvePartOne(): Int {
        return patterns.sumOf {
            val horizontal = findHorizontalSeparator(it) * 100
            val vertical = if (horizontal == 0) findVerticalSeparator(it) else 0
            horizontal + vertical
        }
    }

    override fun solvePartTwo(): Int {
        return patterns.sumOf {
            val horizontal = findHorizontalSeparator(it, 1) * 100
            val vertical = if (horizontal == 0) findVerticalSeparator(it, 1) else 0
            horizontal + vertical
        }
    }

    private fun findHorizontalSeparator(pattern: GridString, smudge: Int = 0): Int {
        var line = 0
        for (size in 1..<pattern.height) {
            val part1 = pattern.take(size).asReversed()
            val part2 = pattern.drop(size).take(part1.size)
            val differences = part1.zip(part2) { line1, line2 ->
                line1.zip(line2).count { (char1, char2) -> char1 != char2 }
            }.sum()
            if (differences == smudge) {
                line = size
                break
            }
        }
        return line
    }

    private fun findVerticalSeparator(pattern: GridString, smudge: Int = 0): Int {
        return findHorizontalSeparator(pattern.rotate(), smudge)
    }

}