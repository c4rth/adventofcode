package org.carth.aoc2020

import org.carth.common.Puzzle

class Day18(input: String) : Puzzle<Long, Long>() {

    val data = input.lines()

    override fun solvePartOne(): Long {
        return data.sumOf { line ->
            evaluate1(line.iterator())
        }
    }

    override fun solvePartTwo(): Long {
        return data.sumOf { line ->
            evaluate2(line.iterator())
        }
    }

    private fun evaluate1(str: CharIterator): Long {
        val numbers = mutableListOf<Long>()
        var op = '?'
        while (str.hasNext()) {
            val next = str.next()
            when {
                next.isDigit() -> numbers.add((next - '0').toLong())
                next == '+' || next == '*' -> op = next
                next == '(' -> numbers.add(evaluate1(str))
                next == ')' -> break
            }
            if (numbers.size == 2) {
                val (x,y) = numbers
                numbers.clear()
                numbers.add(if (op == '+') x + y else x * y)
            }
        }
        return numbers.first()
    }

    private fun evaluate2(str: CharIterator): Long {
        var toMult = 1L
        var toAdd = 0L
        while (str.hasNext()) {
            val next = str.next()
            when {
                next.isDigit() -> toAdd += (next - '0').toLong()
                next == '(' -> toAdd += (evaluate2(str))
                next == ')' -> break
                next == '*' -> {
                    toMult *= toAdd
                    toAdd = 0L
                }
            }
        }
        toMult *= toAdd
        return toMult
    }

}
