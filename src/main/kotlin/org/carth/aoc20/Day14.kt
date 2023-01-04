package org.carth.aoc20

import org.carth.common.Puzzle

class Day14(input: String) : Puzzle<Long, Long>() {

    private val data = input.lines()
    private val regex = Regex("mem\\[(.*)\\] = (.*)")

    private var mask = ""
    private val memory = mutableMapOf<Long, Long>()

    override fun solvePartOne(): Long {
        return solve { strIndex, strValue ->
            val index = strIndex.toLong()
            val value = strValue.toBinary()
            val masked = value.toCharArray()
            mask.forEachIndexed { i, m ->
                if (m != 'X') masked[i] = m
            }
            memory[index] = String(masked).toLong(2)
        }
    }

    override fun solvePartTwo(): Long {
        return solve {strIndex, strValue ->
            val index = strIndex.toBinary()
            val value = strValue.toLong()
            val masked = index.toCharArray()
            mask.forEachIndexed { i, m ->
                if (m != '0') masked[i] = m
            }
            val addresses = getAddresses(String(masked))
            for (address in addresses) {
                memory[address.toLong(2)] = value
            }
        }
    }

    private fun solve(run: (String, String) -> Unit): Long {
        data.forEach { line ->
            if (line.startsWith("mask")) {
                mask = line.drop(7)
            } else { // mem
                val (strIndex, strValue) = regex.find(line)!!.destructured
                run(strIndex, strValue)
            }
        }
        return memory.values.sum()
    }

    private fun String.toBinary(): String = this.toLong().toString(2).padStart(36, '0')

    private fun getAddresses(index: String): List<String> {
        var addresses = mutableListOf<String>()
        addresses.add(index.substringBefore("X"))
        index.drop(addresses.first().length).forEach { bit ->
            val newAddresses = mutableListOf<String>()
            addresses.forEach { str ->
                if (bit == 'X') {
                    newAddresses.add(str + "0")
                    newAddresses.add(str + "1")
                } else {
                    newAddresses.add(str + bit)
                }
            }
            addresses = newAddresses
        }
        return addresses
    }

}
