package sorting

import java.util.Scanner
import kotlin.math.roundToInt

val scanner  = Scanner(System.`in`)

fun main(args: Array<String>) {
    if (args.size == 1 && !args.contains("-sortingType")) word()
    when {
        args.contains("byCount")  -> when {
            args.contains("long") -> long("byCount")
            args.contains("word") -> word("byCount")
            args.contains("line") -> line("byCount")
        }
        args.contains("natural") || (!args.contains("natural") && !args.contains("byCount")) -> when {
            args.contains("long") -> long("natural")
            args.contains("word") -> word("natural")
            args.contains("line") -> line("natural")
        }
        else -> word()
    }
}
fun long(sortType: String = "natural") {
    var numbers = mutableListOf<Int>()
    while (scanner.hasNext()) numbers.add(scanner.nextInt())
    val numberString = mutableListOf<String>()
    numbers = numbers.sorted().toMutableList()
    numbers.forEach { numberString.add(it.toString()) }
    when (sortType) {
        "byCount" -> sortedByCount(numberString,"numbers", numberString.size)
        "natural" -> display(numbers.sorted().joinToString(" "), numbers.size, "numbers")
    }
}
fun word(sortType: String = "natural") {
    val words = mutableListOf<String>()
    while (scanner.hasNext()) words.add(scanner.next())
    when (sortType) {
        "byCount" -> sortedByCount (words.sorted().toMutableList(), "words", words.size)
        "natural" -> display(words.sorted().joinToString(" "), words.size, "words")
    }
}
fun line(sortType: String = "natural") {
    val lines = mutableListOf<String>()
    while (scanner.hasNextLine()) lines.add(scanner.nextLine())
    when (sortType) {
        "byCount" -> sortedByCount(lines.sorted().toMutableList(), "lines", lines.size)
        "natural" -> display(lines.sorted().joinToString("\n"), lines.size, "lines")
    }
}
fun sortedByCount(keys: MutableList<String>, total: String, size: Int) {
    val countMap = keys.groupingBy { it }.eachCount()
    val sortedMap = countMap.toList().sortedBy { (_, value) -> value}.toMap().toMutableMap()
    println("Total $total: $size.")
    for ((k, v) in sortedMap) print("$k: $v time(s), ${(v * 100.0 / size).roundToInt()}%\n")
}
fun display(sortedData: String, size: Int, dataType: String) {
    println("Total $dataType: $size.")
    print("Sorted data: ")
    if (dataType != "lines")  println(sortedData) else println("\n$sortedData")
}

