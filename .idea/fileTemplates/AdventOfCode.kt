package org.carth.${PackageName}

import org.carth.common.Puzzle

class ${NAME}(input: String) : Puzzle<Int, Int>() {

    private val data = input.split(System.lineSeparator())

    override fun solvePartOne(): Int {
        return 1
    }

    override fun solvePartTwo(): Int {
        return 10
    }
}