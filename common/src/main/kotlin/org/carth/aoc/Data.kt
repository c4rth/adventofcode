package org.carth.aoc

import java.io.File

class Data(val filename: String, val value: String) {
    fun lines() = value.lines()
    fun listCharArray() = value.lines().map { it.toCharArray() }

    companion object {
        @JvmStatic
        fun read(day: String, type: String, suffix: String): Data {
            val fileSuffix = if (suffix.isEmpty()) "" else "-$suffix"
            val filename = "$day/$type$fileSuffix.txt"
            val fileUri = Companion::class.java.classLoader.getResource(filename)?.toURI()
            return Data(filename, File(fileUri!!).readText())
        }
    }
}