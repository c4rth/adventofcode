package org.carth.aoc20

import org.carth.common.Puzzle


class Day04(input: String) : Puzzle<String, String>() {

    private val passports = input.split(System.lineSeparator() + System.lineSeparator())
    private val mandatoryFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    override fun solvePartOne(): String {
        var total = 0
        passports.forEach { passport ->
            val fields = passport.split(" ", ":", System.lineSeparator())
            if (fields.containsAll(mandatoryFields)) total++
        }
        return total.toString()
    }

    override fun solvePartTwo(): String {
        var total = 0
        passports.forEach { passport ->
            val fields = passport.split(" ", ":", System.lineSeparator())
            if (fields.containsAll(mandatoryFields)) {
                val values = fields.chunked(2)
                var valids = 0
                values.forEach { value ->
                    val valid = when (value[0]) {
                        "byr" -> (value[1].toIntOrNull() ?: 0) in 1920..2002
                        "iyr" -> (value[1].toIntOrNull() ?: 0) in 2010..2020
                        "eyr" -> (value[1].toIntOrNull() ?: 0) in 2020..2030
                        "hgt" -> {
                            if (value[1].endsWith("cm")) {
                                (value[1].dropLast(2).toIntOrNull() ?: 0) in 150..193
                            } else if (value[1].endsWith("in")) {
                                (value[1].dropLast(2).toIntOrNull() ?: 0) in 59..76
                            } else {
                                false
                            }
                        }

                        "hcl" -> "#(?:[0-9a-f]{2}){3}".toRegex().matches(value[1])
                        "ecl" -> value[1] in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                        "pid" -> value[1].length == 9 && value[1].toIntOrNull() != null
                        else -> false
                    }
                    if (valid) valids++
                }
                if (valids == 7) total++
            }
        }
        return total.toString()
    }

}