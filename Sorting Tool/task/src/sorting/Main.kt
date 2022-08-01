package sorting

import java.util.Scanner
import kotlin.system.exitProcess

val scanner  = Scanner(System.`in`)

fun main(args: Array<String>) {
    if (args.size == 1 && !args.contains("-sortIntegers")) word()
    when {
        args.contains("-sortIntegers") -> sort()
        args[0] == "-dataType" && args[1] == "long" -> long()
        args[0] == "-dataType" && args[1] == "line" -> line()
        args[0] == "-dataType" && args[1] == "word" -> word()
        else -> word()
    }
}
fun sort() {
    val numbers = mutableListOf<Int>()
    while (scanner.hasNext()) numbers.add(scanner.nextInt())
    val sortedNumbers = numbers.sorted().joinToString(" ")
    println("Total numbers: ${numbers.size}.")
    println("Sorted data: $sortedNumbers")
}
fun long() {
    val numbers = mutableListOf<Int>()
    while (scanner.hasNext()) numbers.add(scanner.nextInt())
    val max: Int = numbers.maxOrNull() ?: 0
    var noOfTimes = 0
    for (i in numbers) if (i == max) noOfTimes++
    result("numbers", "greatest number",numbers.size, max.toString(), noOfTimes)
}
fun line() {
    val lines = mutableListOf<String>()
    var max = ""
    var noOfTimes = 0
    while (scanner.hasNextLine()) lines.add(scanner.nextLine())
    lines.forEach { if(it.length > max.length) max = it }
    lines.forEach { if (it == max) noOfTimes++ }
    result("lines", "longest line",lines.size, max, noOfTimes)
}
fun word() {
    val words = mutableListOf<String>()
    while (scanner.hasNext()) words.add(scanner.next())
    var max = ""
    var noOfTimes = 0
    words.forEach { if(it.length > max.length) max = it }
    words.forEach { if (it == max) noOfTimes++ }
    result("words", "longest word",words.size, max, noOfTimes)
}
fun result(total: String, longest: String, size: Int, max: String, noOfTimes: Int) {
    val percentage: Double = noOfTimes.toDouble() / size * 100.0
    val newL = if (total == "lines") "\n" else " "
    println("Total $total: $size.")
    println("The $longest:$newL$max$newL($noOfTimes time(s),${percentage.toInt()}%).")
    exitProcess(0)
}
