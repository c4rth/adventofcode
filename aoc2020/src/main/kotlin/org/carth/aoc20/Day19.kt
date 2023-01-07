package org.carth.aoc20

import org.carth.common.Puzzle

class Day19(input: String) : Puzzle<Int, Int>() {

    private val blocks = input.split(System.lineSeparator() + System.lineSeparator())
    private val rules = blocks[0].lines().associate { line ->
        val rule = Rule(line)
        rule.id to rule
    }
    private val messages = blocks[1].lines()

    override fun solvePartOne(): Int {
        val reg = ("^" + rules[0]?.toRegExp(rules) + "\$").toRegex()
        return messages.count { it.matches(reg) }
    }

    override fun solvePartTwo(): Int {
        val rule42 = rules[42]!!.toRegExp(rules)
        val rule31 = rules[31]!!.toRegExp(rules)
        var new11 = ""
        repeat(5) { i -> new11 += "(42{${i + 1}}31{${i + 1}})|" }
        val rules0 = "^(42+)(${new11.dropLast(1)})$".replace("42", rule42).replace("31", rule31)
        val reg = rules0.toRegex()
        return messages.count { it.matches(reg) }
    }

    class Rule(line: String) {
        val id: Int
        private val value: Char?
        private val rules: List<List<Int>>?

        init {
            id = line.substringBefore(":").toInt()
            val v = line.substringAfter(": ")
            if (v.startsWith("\"")) {
                value = v[1]
                rules = null
            } else {
                value = null
                rules = v.split(" | ").map { rule ->
                    rule.split(" ").map { it.toInt() }
                }
            }
        }

        fun toRegExp(allRules: Map<Int, Rule>): String {
            if (value != null) return value.toString()
            var str = "("
            rules!!.forEachIndexed { index, orRules ->
                orRules.forEach { andRule ->
                    str += allRules[andRule]?.toRegExp(allRules)
                }
                if (index + 1 < rules.size) str += "|"
            }
            str += ")"
            return str
        }

    }

}
