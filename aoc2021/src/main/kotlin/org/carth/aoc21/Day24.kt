package org.carth.aoc21

import org.carth.common.Puzzle


class Day24(input: String) : Puzzle<Long, Long>() {

    private val data = input.lines()

    // Faster solution : not mine : credits to lolofonik
    private fun run(part2: Boolean): Long {
        val blocks = data.chunked(18)
        val result = MutableList(14) { -1 }
        val buffer = ArrayDeque<Pair<Int, Int>>()
        val best = if (part2) 1 else 9
        blocks.forEachIndexed { index, instructions ->
            if ("div z 26" in instructions) {
                val offset = instructions.lastOf("add x")
                val (lastIndex, lastOffset) = buffer.removeFirst()
                val difference = offset + lastOffset
                if (difference >= 0) {
                    result[lastIndex] = if (part2) best else best - difference
                    result[index] = if (part2) best + difference else best
                } else {
                    result[lastIndex] = if (part2) best - difference else best
                    result[index] = if (part2) best else best + difference
                }
            } else buffer.addFirst(index to instructions.lastOf("add y"))
        }
        return result.joinToString("").toLong()
    }

    override fun solvePartOne() = run(false)

    override fun solvePartTwo() = run(true)


    private fun List<String>.lastOf(command: String) = last { it.startsWith(command) }.split(" ").last().toInt()

// DUMB solutions : mine
//    override fun solvePartOne(): Int {
//
//        var valid = 0L
//
//        //var startInput = 99_999_999_999_999
//        var startInput = 59_692_994_996_000
//
//        while (startInput >= 11_111_111_111_111) {
//            val input = startInput.toString()
//            if (!input.contains("0")) {
//                val values = mutableMapOf("w" to 0, "x" to 0, "y" to 0, "z" to 0)
//                var inpIndex = 0
//                data.forEach { line ->
//                    val instruction = line.split(" ")
//                    when (instruction[0]) {
//                        "inp" -> {
//                            values[instruction[1]] = input[inpIndex++].digitToInt()
//                        }
//                        "add" -> {
//                            val add = instruction[2].toIntOrNull() ?: values[instruction[2]]!!
//                            values[instruction[1]] = values[instruction[1]]!! + add
//                        }
//                        "mul" -> {
//                            val mul = instruction[2].toIntOrNull() ?: values[instruction[2]]!!
//                            values[instruction[1]] = values[instruction[1]]!! * mul
//                        }
//                        "div" -> {
//                            val div = instruction[2].toIntOrNull() ?: values[instruction[2]]!!
//                            values[instruction[1]] = values[instruction[1]]!! / div
//                        }
//                        "mod" -> {
//                            val mod = instruction[2].toIntOrNull() ?: values[instruction[2]]!!
//                            values[instruction[1]] = values[instruction[1]]!! % mod
//                        }
//                        "eql" -> {
//                            val eql = instruction[2].toIntOrNull() ?: values[instruction[2]]!!
//                            values[instruction[1]] = if (values[instruction[1]]!! == eql) 1 else 0
//                        }
//                    }
//                }
//                println("$input : ${values["z"]}")
//                if (values["z"] == 0) {
//                    println("add $input")
//                    valid = startInput
//                    break
//                }
//            }
//            startInput -= 1L
//        }
//        return valid
//    }
//
//    override fun solvePartTwo(): Int {
//
//        var valid = 0L
//
//        //var startInput = 99_999_999_999_999
//        var startInput = 16_181_111_641_510L
//
//        while (startInput <= 99_999_999_999_999L) {
//            val input = startInput.toString()
//            if (!input.contains("0")) {
//                val values = mutableMapOf("w" to 0, "x" to 0, "y" to 0, "z" to 0)
//                var inpIndex = 0
//                data.forEach { line ->
//                    val instruction = line.split(" ")
//                    when (instruction[0]) {
//                        "inp" -> {
//                            values[instruction[1]] = input[inpIndex++].digitToInt()
//                        }
//                        "add" -> {
//                            val add = instruction[2].toIntOrNull() ?: values[instruction[2]]!!
//                            values[instruction[1]] = values[instruction[1]]!! + add
//                        }
//                        "mul" -> {
//                            val mul = instruction[2].toIntOrNull() ?: values[instruction[2]]!!
//                            values[instruction[1]] = values[instruction[1]]!! * mul
//                        }
//                        "div" -> {
//                            val div = instruction[2].toIntOrNull() ?: values[instruction[2]]!!
//                            values[instruction[1]] = values[instruction[1]]!! / div
//                        }
//                        "mod" -> {
//                            val mod = instruction[2].toIntOrNull() ?: values[instruction[2]]!!
//                            values[instruction[1]] = values[instruction[1]]!! % mod
//                        }
//                        "eql" -> {
//                            val eql = instruction[2].toIntOrNull() ?: values[instruction[2]]!!
//                            values[instruction[1]] = if (values[instruction[1]]!! == eql) 1 else 0
//                        }
//                    }
//                }
//                if (values["z"] == 0) {
//                    println("found $input")
//                    valid = startInput
//                    break
//                }
//            }
//            startInput += 1L
//        }
//        return valid
//    }
}