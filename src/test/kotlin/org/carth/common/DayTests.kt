package org.carth.common

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import java.io.File
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

@ExtendWith(TimingExtension::class)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
abstract class DayTests<T : Puzzle<String, String>>(private val clazz: KClass<T>) {

    enum class Type {
        TEST, INPUT
    }

    enum class Part {
        ONE, TWO
    }


    private val year: String = this.javaClass.`package`.name.takeLast(2)
    private val day: String = this.javaClass.simpleName.take(5).lowercase(Locale.getDefault())

    private fun String.toURI() =
        object {}.javaClass.classLoader.getResource(this)?.toURI()
            ?: throw IllegalArgumentException("Cannot find Resource: $this")

    private fun getFilename(type: Type, suffix: String = ""): String =
        "aoc$year/$day/${type.toString().lowercase(Locale.getDefault())}$suffix.txt"

    fun readInput(type: Type, suffix: String = ""): String = File(getFilename(type, suffix).toURI()).readText()

    private fun getInstance(data: String): T = clazz.primaryConstructor!!.call(data)

    fun solve(part: Part, type: Type, suffix: String = "", expected: String) {
        val input = readInput(type, suffix)
        val answer = if (part == Part.ONE) getInstance(input).solvePartOne() else getInstance(input).solvePartTwo()
        Assertions.assertEquals(expected, answer)
    }


}