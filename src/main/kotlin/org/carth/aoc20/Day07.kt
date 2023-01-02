package org.carth.aoc20

import org.carth.common.Puzzle

class Day07(input: String) : Puzzle<String, String>() {

    private val bags = input.lines().associate { line ->
        val split = line.split("contain", ", ", ".").dropLast(1)
        val key = split[0].dropLast(6)
        val cont = split.drop(1).map { bag ->
            val num = if (bag.trim()[0].isDigit()) bag.trim()[0] - '0' else 0
            num to bag.replace("bags", "").replace("bag", "").drop(2).trim()
        }
        key to cont
    }

    override fun solvePartOne(): String {
        val bag = "shiny gold"
        return canContainBag(bag).distinct().size.toString()
    }

    private fun canContainBag(bag: String): List<String> {
        val list = mutableListOf<String>()
        bags.forEach { (key, bags) ->
            if (bags.any { (_, bag2) -> bag == bag2 }) {
                list.add(key)
                list.addAll(canContainBag(key))
            }
        }
        return list
    }


    override fun solvePartTwo(): String {
        val bag = "shiny gold"
        return sumBag(bag).toString()
    }

    private fun sumBag(bag: String): Int {
        return bags[bag]?.sumOf { (num, bag2) ->
            num + (num * sumBag(bag2))
        } ?: 0
    }
}