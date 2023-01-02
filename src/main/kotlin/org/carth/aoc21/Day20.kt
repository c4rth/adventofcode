package org.carth.aoc21

import org.carth.common.Puzzle

typealias Image = Array<IntArray>

class Day20(input: String) : Puzzle<Int, Int>() {

    private val data = input.lines()

    override fun solvePartOne(): Int = solve(2)

    override fun solvePartTwo(): Int = solve(50)

    private fun solve(times: Int): Int {
        var (algorithm, image) = parse()
        var default = 0
        val isFlashing = algorithm[0] == 1
        repeat(times) {
            val nextImage = Array(image.size + 2) { IntArray(image.size + 2) { default } }
            for (l in nextImage.indices) {
                for (c in nextImage.indices) {
                    nextImage[l][c] = algorithm[getBinary(image, l - 1, c - 1, default)]
                }
            }
            default = if (isFlashing && default == 0) 1 else 0
            image = nextImage
        }
        return image.sumOf { it.sum() }
    }

    private fun getBinary(image: Image, l: Int, c: Int, default: Int): Int {
        var acc = 0
        for (il in -1..1) {
            for (ic in -1..1) {
                val value =
                    if ((l + il) in image.indices && (c + ic) in image.indices) image[l + il][c + ic] else default
                acc = acc * 2 + value
            }
        }
        return acc
    }

    private fun parse(): Pair<IntArray, Image> {
        val algorithm = data.first().map { if (it == '#') 1 else 0 }.toIntArray()
        val image = data.drop(2).map { line ->
            line.map { if (it == '#') 1 else 0 }.toIntArray()
        }.toTypedArray()
        return algorithm to image
    }
}