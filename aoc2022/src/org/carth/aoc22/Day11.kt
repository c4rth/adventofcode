package org.carth.aoc22

import org.carth.common.Puzzle

class Day11(input: String) : Puzzle<Long, Long>() {
    private val data = input.lines()
    override fun solvePartOne() = solve(20, 3)

    override fun solvePartTwo() = solve(10000, 1)

    private fun parse(): List<Monkey> {
        val monkeys = ArrayList<Monkey>()
        for (i in data.indices step 7) {
            val items = data[i + 1].substringAfter("items: ").split(",").map { it.trim().toLong() }.toMutableList()
            val (opn, opd) = data[i + 2].substringAfter("new = old ").split(" ")
            val opdl = if (opd == "old") -1L else opd.toLong()
            val divisor = data[i + 3].substringAfter("by ").toLong()
            val trueMonkey = data[i + 4].substringAfter(" monkey ").toInt()
            val falseMonkey = data[i + 5].substringAfter(" monkey ").toInt()
            monkeys.add(Monkey(0, items, opn, opdl, divisor, trueMonkey, falseMonkey))
        }
        return monkeys
    }

    private fun solve(times: Int, worryDivisor: Int): Long {
        val monkeys = parse()
        val ppcm = monkeys.map { it.divisor }.distinct().reduce { acc, value -> acc * value }
        repeat(times) {
            monkeys.forEach { monkey ->
                monkey.items.forEach { item ->
                    monkey.inspected++
                    val level = calcLevel(item, monkey.operator, if (monkey.operand == -1L) item else monkey.operand)
                    val relief = (level / worryDivisor) % ppcm
                    val idx = if (relief % monkey.divisor == 0L) monkey.indexTrue else monkey.indexFalse
                    monkeys[idx].items.add(relief)
                }
                monkey.items.clear()
            }
        }
        return monkeys.map { it.inspected }.sorted().takeLast(2).reduce(Long::times)
    }

    private fun calcLevel(worryLevel: Long, operator: String, operandL: Long): Long {
        return when (operator) {
            "*" -> worryLevel * operandL
            "+" -> worryLevel + operandL
            "/" -> worryLevel / operandL
            else -> worryLevel - operandL
        }
    }

    class Monkey(
        var inspected: Long,
        val items: MutableList<Long>,
        val operator: String,
        val operand: Long,
        val divisor: Long,
        val indexTrue: Int,
        val indexFalse: Int
    )

}