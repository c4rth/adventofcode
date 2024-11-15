package org.carth.aoc

class Data(val value: String) {
    fun lines() = value.lines()
    fun listCharArray() = value.lines().map { it.toCharArray() }
}