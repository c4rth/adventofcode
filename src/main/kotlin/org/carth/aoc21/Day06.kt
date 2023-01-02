package org.carth.aoc21

import org.carth.common.Puzzle

class Day06(input: String) : Puzzle<Long, Long>() {

    private val data = input.lines()

    private fun process(lifes: LongArray) {
        val newFish = lifes[0]
        lifes.copyInto(lifes, 0, 1, 9)
        lifes[6] += newFish
        lifes[8] = newFish
    }

    private fun initLifes(): LongArray {
        val fishes = data.first().split(",").map { it.toInt() }
        val lifes = LongArray(10) { 0 }
        for (index in 1..6) {
            lifes[index] = fishes.filter { it == index }.size.toLong()
        }
        return lifes
    }

    override fun solvePartOne(): Long {
        val lifes = initLifes()
        for (day in 1..80) {
            process(lifes)
        }
        return lifes.sum()
    }

    override fun solvePartTwo(): Long {
        val lifes = initLifes()
        for (day in 1..256) {
            process(lifes)
        }
        return lifes.sum()
    }
}