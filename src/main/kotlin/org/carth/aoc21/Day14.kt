package org.carth.aoc21

import org.carth.common.Puzzle

typealias Elements = Map<String, Long>
typealias Rules = Map<String, String>

class Day14(private val data: List<String>) : Puzzle<Long, Long>() {

    override fun solvePartOne(): Long {
        return solve(10)
    }

    override fun solvePartTwo(): Long {
        return solve(40)
    }

    private fun solve(times: Int): Long {
        var (polymer, rules, elements) = readInputs()
        repeat(times) {
            elements = mutableMapOf<String, Long>().withDefault { 0 }.apply {
                elements.forEach { (element, count) ->
                    val rule = rules.getValue(element)
                    val first = "${element[0]}$rule"
                    val second = "$rule${element[1]}"
                    put(first, getValue(first) + count)
                    put(second, getValue(second) + count)
                }
            }
        }
        val counts = countElements(elements, polymer)
        return counts.last() - counts.first()
    }

    private fun readInputs(): Triple<String, Rules, Elements> {
        val elements = mutableMapOf<String, Long>().withDefault { 0 }.apply {
            data.first().windowed(2).forEach { element ->
                put(element, getValue(element) + 1)
            }
        }
        val rules = mutableMapOf<String, String>().apply {
            data.drop(2).forEach { line ->
                line.split(" -> ").let { rule ->
                    put(rule[0], rule[1])
                }
            }
        }
        return Triple(data.first(), rules, elements)
    }

    private fun countElements(elements: Elements, polymer: String): List<Long> =
        mutableMapOf<Char, Long>().withDefault { 0 }.apply {
            elements.forEach { (element, count) ->
                put(element[0], getValue(element[0]) + count)
            }
            put(polymer.last(), getValue(polymer.last()) + 1)
        }.values.sorted()


}