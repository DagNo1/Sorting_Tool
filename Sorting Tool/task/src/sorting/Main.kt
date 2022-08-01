package sorting

import java.util.Scanner

fun main() {
    val scanner  = Scanner(System.`in`)
    val numbers = mutableListOf<Int>()
    while(scanner.hasNext()) numbers.add(scanner.nextInt())
    val max: Int = numbers.maxOrNull() ?: 0
    var noOfTimes = 0
    for (i in numbers) if (i == max) noOfTimes++
    println("Total numbers: ${numbers.size}.")
    println("The greatest number: $max ($noOfTimes time(s)).")
}
