package org.carth.aoc2023

import org.carth.common.Puzzle

class Day08(input: String) : Puzzle<Int, Long>() {

    private val data = input.split(System.lineSeparator())

    private val instr = data[0]

    private val nodes = data.drop(2).associate { line ->
        line.split(" = ").let { (key, node) ->
            val tos = node.removeSurrounding("(", ")").split(", ")
            key to Node(tos[0], tos[1])
        }
    }

    override fun solvePartOne(): Int {
        var currentKey = "AAA"
        var steps = 0
        var index = 0
        while (currentKey != "ZZZ") {
            currentKey = if (instr[index] == 'L') nodes[currentKey]!!.left else nodes[currentKey]!!.right
            steps++
            index = (index + 1) % instr.length
        }
        return steps
    }

    override fun solvePartTwo(): Long {
        val currentKeys = nodes.keys.filter { it.endsWith("A") }.toMutableList()
        val steps = currentKeys.map { key ->
            var currentKey = key
            var index = 0
            var step = 0
            while (!currentKey.endsWith("Z")) {
                currentKey = if (instr[index] == 'L') nodes[currentKey]!!.left else nodes[currentKey]!!.right
                step++
                index = (index + 1) % instr.length
            }
            step.toLong()
        }
        return lcm(steps)
    }

    private fun lcm(a: Long, b: Long): Long {
        return a / gcd(a, b) * b
    }

    private fun gcd(a: Long, b: Long): Long {
        return if (b == 0L) a else gcd(b, a % b)
    }

    private fun lcm(input: List<Long>): Long {
        return input.reduce { acc, l -> lcm(acc, l) }
    }

    data class Node(val left: String, val right: String)
}