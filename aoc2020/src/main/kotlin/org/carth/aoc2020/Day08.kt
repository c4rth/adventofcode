package org.carth.aoc2020

import org.carth.common.Puzzle

class Day08(input: String) : Puzzle<Int, Int>() {

    private val program = Program(input.lines().map { instr ->
        Triple(instr.take(3), instr.drop(4).toInt(), false)
    }.toMutableList())

    override fun solvePartOne(): Int {
        while (!program.doubleExec) {
            program.execute()
        }
        return program.total
    }

    override fun solvePartTwo(): Int {
        program.instructions.forEachIndexed { idx, (instr, num, exec) ->
            if (instr != "acc") {
                val newInstructions = program.instructions.toMutableList()
                val newInstr = if (instr == "nop") "jmp" else "nop"
                newInstructions[idx] = Triple(newInstr, num, exec)
                val newProgram = Program(newInstructions)
                while (!newProgram.doubleExec && newProgram.index < newProgram.instructions.size) {
                    newProgram.execute()
                }
                if (newProgram.index == newProgram.instructions.size)
                    return newProgram.total
            }
        }

        return 0
    }

    class Program(val instructions: MutableList<Triple<String, Int, Boolean>>) {

        var index = 0
        var total = 0
        var doubleExec = false

        fun execute() {
            val line = instructions[index]
            val prevIndex = index
            if (line.third) {
                doubleExec = true
            } else {
                when (line.first) {
                    "acc" -> {
                        total += line.second
                        index++
                    }

                    "nop" -> {
                        index++
                    }

                    "jmp" -> {
                        index += line.second
                    }
                }
                instructions[prevIndex] = Triple(line.first, line.second, true)
            }
        }
    }
}