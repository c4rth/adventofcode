package org.carth.aoc21

import org.carth.common.Puzzle

class Day10(input: String) : Puzzle<Int, Long>() {
    private val data = input.lines()
    private val brackets = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')

    override fun solvePartOne(): Int {
        val values = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
        var total = 0
        val stack = ArrayDeque<Char>()
        data.forEach { line ->
            stack.clear()
            for (c in line) {
                if (c == '(' || c == '[' || c == '{' || c == '<') {
                    stack.addLast(c)
                } else {
                    val last = stack.removeLast()
                    if (brackets[last] != c) {
                        total += values.getOrDefault(c, 0)
                        break
                    }
                }
            }
        }
        return total
    }

    override fun solvePartTwo(): Long {
        val values = mapOf('(' to 1, '[' to 2, '{' to 3, '<' to 4)
        val answers = mutableListOf<Long>()
        val stack = ArrayDeque<Char>()
        data.forEach { line ->
            stack.clear()
            var isLineOk = true
            for (c in line) {
                if (c == '(' || c == '[' || c == '{' || c == '<') {
                    stack.addLast(c)
                } else {
                    val last = stack.removeLast()
                    if (brackets[last] != c) {
                        isLineOk = false
                        break
                    }
                }
            }
            if (isLineOk) {
                var subTotal = 0L
                while (stack.isNotEmpty()) {
                    subTotal = subTotal * 5 + values.getOrDefault(stack.removeLast(), 0)
                }
                answers.add(subTotal)
            }
        }
        answers.sort()
        return answers[(answers.size - 1) / 2]
    }
}