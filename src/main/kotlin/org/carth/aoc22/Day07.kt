package org.carth.aoc22

import org.carth.common.Puzzle

class Day07(private val data: List<String>) : Puzzle<Int, Int>() {

    private val directories = parseTerminal()

    override fun solvePartOne(): Int =
        directories.filter { dir -> dir.size <= 100_000 }.sumOf { dir -> dir.size }

    override fun solvePartTwo(): Int {
        val freeSpace = 70_000_000 - directories.first { dir -> dir.parent == null }.size
        val freeSpaceNeeded = 30_000_000 - freeSpace
        return directories.filter { dir -> dir.size > freeSpaceNeeded }.minOf { dir -> dir.size }
    }

    private fun parseTerminal(): List<Node> {
        val directories = mutableListOf<Node>()
        var currentDir = DirNode()
        directories.add(currentDir)
        data.drop(1).forEach { line ->
            // $ ls is ignored
            if (line.startsWith("$ cd")) {
                line.drop(5).also { name ->
                    currentDir = if (name == "..") currentDir.parent!! else currentDir.subdirectories.getValue(name)
                }
            } else if (line.startsWith("dir")) {
                line.drop(4).also { name ->
                    DirNode(currentDir).also { dir ->
                        directories.add(dir)
                        currentDir.subdirectories[name] = dir
                    }
                }
            } else if (line.first().isDigit()) {
                val size = line.split(" ").first()
                currentDir.files.add(FileNode(currentDir, size.toInt()))
            }
        }
        return directories
    }

    abstract class Node(val parent: DirNode? = null) {
        abstract val size: Int
    }

    class FileNode(parent: DirNode, override val size: Int = 0) : Node(parent)

    class DirNode(parent: DirNode? = null) : Node(parent) {
        val files = mutableListOf<FileNode>()
        val subdirectories = mutableMapOf<String, DirNode>()
        override val size: Int
            get() =
                files.sumOf { file -> file.size } + subdirectories.values.sumOf { dir -> dir.size }
    }
}
