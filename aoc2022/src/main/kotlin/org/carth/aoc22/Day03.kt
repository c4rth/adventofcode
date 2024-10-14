package org.carth.aoc22

import org.carth.common.Puzzle

class Day03(input: String) : Puzzle<Int, Int>() {

    private val sacks = input.lines().map { line -> Rucksack(line) }

    override fun solvePartOne() = sacks.sumOf { priority(it.findMistake()) }

    override fun solvePartTwo() =
        sacks.chunked(3)
            .map { group ->
                group[0].findBadge(group[1], group[2])
            }
            .sumOf { c -> priority(c) }

    private fun priority(c: Char) = if (c <= 'Z') c.code - 38 else c.code - 96


    class Rucksack(private val content: String) {
        fun findMistake(): Char {
            val halfLength = content.length / 2
            val left = content.substring(0 until halfLength)
            val right = content.substring(halfLength)
            //return left.toList().intersect(right.toList().toSet()).first()
            return left.first { c -> right.contains(c) }
        }

        fun findBadge(rucksack2: Rucksack, rucksack3: Rucksack) =
            content.filter { rucksack2.content.contains(it) }.first { rucksack3.content.contains(it) }
        //content.toList().intersect(rucksack2.content.toSet()).intersect(rucksack3.content.toSet()).first()
    }
}

