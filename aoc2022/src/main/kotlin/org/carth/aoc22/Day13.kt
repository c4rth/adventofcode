package org.carth.aoc22

import org.carth.common.Puzzle

class Day13(data: String) : Puzzle<Int, Int>() {

    private val packets = data.split(System.lineSeparator() + System.lineSeparator())
        .map { lines ->
            lines.split(System.lineSeparator())
                .map { line -> Packet.parse(line) }
        }

    override fun solvePartOne(): Int =
        packets.mapIndexed { index, packets ->
            if (packets[0] < packets[1]) index + 1 else 0
        }.sum()

    override fun solvePartTwo(): Int {
        val packet1 = Packet.parse("[[2]]")
        val packet2 = Packet.parse("[[6]]")
        val allPackets = (packets.flatten().toMutableList() + listOf(packet1, packet2)).sorted()
        return ((allPackets.indexOf(packet1) + 1) * (allPackets.indexOf(packet2) + 1))
    }

    sealed class Packet : Comparable<Packet> {
        companion object {
            fun parse(str: String): Packet {
                return if (str.startsWith("["))
                    PacketList.parse(str)
                else
                    PacketInt(str.toInt())
            }
        }

        private class PacketInt(val value: Int) : Packet() {
            override fun compareTo(other: Packet): Int {
                return if (other is PacketInt)
                    value.compareTo(other.value)
                else
                    PacketList(listOf(this)).compareTo(other)
            }
        }

        private class PacketList(val children: List<Packet>) : Packet() {
            override fun compareTo(other: Packet): Int {
                return when (other) {
                    is PacketInt -> this.compareTo(PacketList(listOf(other)))
                    is PacketList -> children.zip(other.children)
                        .map { (first, second) -> first.compareTo(second) }
                        .firstOrNull { it != 0 } ?: (children.size.compareTo(other.children.size))
                }
            }

            companion object {
                fun parse(str: String): Packet {
                    if (str == "[]") return PacketList(emptyList())
                    var current = ""
                    var brackets = 0
                    val children = mutableListOf<Packet>()
                    str.drop(1).dropLast(1).forEach { char ->
                        if (char == '[') brackets++
                        if (char == ']') brackets--
                        if (char == ',' && brackets == 0) {
                            children.add(Packet.parse(current))
                            current = ""
                        } else
                            current += char
                    }
                    children.add(Packet.parse(current))
                    return PacketList(children)
                }
            }
        }
    }
}