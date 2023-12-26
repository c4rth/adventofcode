package org.carth.common

fun lcm(a: Long, b: Long): Long {
    return a / gcd(a, b) * b
}

fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) a else gcd(b, a % b)
}

fun lcm(input: List<Long>): Long {
    return input.reduce { acc, l -> lcm(acc, l) }
}