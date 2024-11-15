package org.carth.aoc2024

import org.carth.aoc.Data
import org.carth.aoc.Puzzle

fun main() = Day01().solve()

class Day01 : Puzzle<Int, Int>() {

    private val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    @Sample(expected = "142")
    @Puzzle(expected = "55621")
    override fun solvePart1(data: Data) = getIntValues(data).sum()

    @Sample(expected = "142")
    @Sample(suffix="2", expected = "281")
    @Puzzle(expected = "53592")
    override fun solvePart2(data: Data) = getStringValues(data).sum()

    private fun getIntValues(data: Data): List<Int> {
        return data.lines()
            .map { line -> line[line.indexOfFirst { c -> c.isDigit() }].toString() + line[line.indexOfLast { c -> c.isDigit() }] }
            .map { it.toInt() }
    }

    private fun getStringValues(data: Data): List<Int> {
        return data.lines()
            .map { getFirstNumber(it) + getLastNumber(it) }
            .map { it.toInt() }
    }

    private fun mapNumber(number: String): String = (numbers.indexOfFirst { it.startsWith(number) } + 1).toString()

    private fun getFirstNumber(line: String): String {
        val idx = numbers.map { n -> line.indexOf(n) }.filter { i -> i > -1 }.minOrNull() ?: Int.MAX_VALUE
        val idxN = line.indexOfFirst { c -> c.isDigit() }
        if (idxN > -1 && idxN < idx) {
            return line[idxN].toString()
        }
        return mapNumber(line.substring(idx, idx + 2))
    }

    private fun getLastNumber(line: String): String {
        val idx = numbers.map { n -> line.lastIndexOf(n) }.filter { i -> i > -1 }.maxOrNull() ?: Int.MIN_VALUE
        val idxN = line.indexOfLast { c -> c.isDigit() }
        if (idxN > -1 && idxN > idx) {
            return line[idxN].toString()
        }
        return mapNumber(line.substring(idx, idx + 2))
    }
}