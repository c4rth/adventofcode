package org.carth.aoc2023

import org.carth.common.Puzzle

class Day15(input: String) : Puzzle<Long, Long>() {

    private val data = input.split(",").map { Step(it) }

    override fun solvePartOne(): Long {
        return data.sumOf { step -> step.fullHash.toLong() }
    }

    override fun solvePartTwo(): Long {
        val boxes = mutableMapOf<Int, MutableList<Step>>()
        data.forEach { step ->
            val hash = step.hash
            val box = boxes.getOrPut(hash) { mutableListOf() }
            if (step.remove) {
                box.removeIf { it.label == step.label }
            } else {
                val idx = box.indexOfFirst { it.label == step.label }
                if (idx != -1) {
                    box[idx].number = step.number
                } else {
                    box.add(step)
                }
            }
            boxes[hash] = box
        }

        var total = 0L
        for (box in boxes.entries) {
            box.value.forEachIndexed { index, step ->
                total += (box.key + 1) * (index + 1) * step.number
            }
        }
        return total
    }

    private fun hash(str: String): Int {
        return str.map { it.code }.fold(0) { acc, i -> ((acc + i) * 17) % 256 }
    }

    class Step(private val str: String) {
        val label: String
        val remove: Boolean
        var number: Int

        init {
            if (str.last() == '-') {
                label = str.dropLast(1)
                remove = true
                number = 0
            } else {
                val x = str.split("=")
                label = x[0]
                remove = false
                number = x[1].toInt()
            }
        }

        private fun hash(str: String) = str.map { it.code }.fold(0) { acc, i -> ((acc + i) * 17) % 256 }

        val fullHash
            get() = hash(str)

        val hash
            get() = hash(label)

        override fun toString() = "[" + this.label + " " + this.number + "]"
    }

}