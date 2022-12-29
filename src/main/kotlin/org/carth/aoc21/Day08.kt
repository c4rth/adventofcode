package org.carth.aoc21

import org.carth.common.Puzzle

class Day08(private val data: List<String>) : Puzzle<Int, Int>() {

    private fun splitIntputOutput(line: String): Pair<List<String>, List<String>> {
        val values = line.split("|").map { it.trim().split(" ") }
        return Pair(values[0], values[1])
    }

    override fun solvePartOne(): Int {
        var total = 0
        data.forEach { line ->
            val (_, output) = splitIntputOutput(line)
            var subTotal = 0
            subTotal += output.filter { it.length == 2 }.size // 1
            subTotal += output.filter { it.length == 4 }.size // 4
            subTotal += output.filter { it.length == 3 }.size // 7
            subTotal += output.filter { it.length == 7 }.size // 8
            total += subTotal
        }
        return total
    }

    //     0(6)    1(2)    2(5)    3(5)    4(4)     5(5)    6(6)    7(3)    8(7)    9(6)
    //     aaaa    ....    aaaa    aaaa    ....     aaaa    aaaa    aaaa    aaaa    aaaa
    //    b    c  .    c  .    c  .    c  b    c   b    .  b    .  .    c  b    c  b    c
    //    b    c  .    c  .    c  .    c  b    c   b    .  b    .  .    c  b    c  b    c
    //     ....    ....    dddd    dddd    dddd     dddd    dddd    ....    dddd    dddd
    //    e    f  .    f  e    .  .    f  .    f   .    f  e    f  .    f  e    f  .    f
    //    e    f  .    f  e    .  .    f  .    f   .    f  e    f  .    f  e    f  .    f
    //     gggg    ....    gggg    gggg    ....     gggg    gggg    ....    gggg    gggg
    //

    private fun getNumbers(input: List<String>): List<String> {
        val numbers = MutableList(10) { "" }
        // easy numbers : 1, 4, 7, 8
        numbers[1] = input.first { it.length == 2 }.sortChar()
        numbers[4] = input.first { it.length == 4 }.sortChar()
        numbers[7] = input.first { it.length == 3 }.sortChar()
        numbers[8] = input.first { it.length == 7 }.sortChar()
        input.forEach { item ->
            if (item.length == 5) { // 2, 3, 5
                if (item.containAllChars(numbers[1])) {
                    numbers[3] = item.sortChar()
                } else if (item.containAllChars(numbers[4].remove(numbers[1]))) {
                    numbers[5] = item.sortChar()
                } else {
                    numbers[2] = item.sortChar()
                }
            } else if (item.length == 6) { // 0, 9, 6
                if (item.containAllChars(numbers[4])) {
                    numbers[9] = item.sortChar()
                } else if (item.containAllChars(numbers[1])) {
                    numbers[0] = item.sortChar()
                } else {
                    numbers[6] = item.sortChar()
                }
            }
        }
        return numbers
    }

    override fun solvePartTwo(): Int {
        var total = 0
        data.forEach { line ->
            val (input, output) = splitIntputOutput(line)
            val numbers = getNumbers(input)
            val sb = StringBuilder()
            output.forEach { item ->
                sb.append(numbers.indexOf(item.sortChar()))
            }
            total += sb.toString().toInt()
        }
        return total
    }

    private fun String.sortChar(): String = String(this.toCharArray().apply { sort() })

    private fun String.containAllChars(other: String): Boolean = this.toList().containsAll(other.toList())

    private fun String.remove(toRemove: String): String = this.replace("[$toRemove]".toRegex(), "")
}