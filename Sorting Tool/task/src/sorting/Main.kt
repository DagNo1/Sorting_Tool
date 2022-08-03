package sorting

import java.util.Scanner
import kotlin.math.roundToInt
import kotlin.system.exitProcess

val scanner  = Scanner(System.`in`)
val commands =  listOf("-dataType", "-sortingType")
val dataTypes = listOf("long", "word", "line")
val sortTypes = listOf("natural", "byCount")
val allConfigurations = commands + dataTypes + sortTypes

fun main(args: Array<String>) {
    var typeOfData = try { args[args.indexOf(commands[0]) + 1] } catch (e: ArrayIndexOutOfBoundsException) {""}
    var typeOfSort = try { args[args.indexOf(commands[1]) + 1] } catch (e: ArrayIndexOutOfBoundsException) {""}
    checkArgs(args, typeOfData, typeOfSort)
    if (!dataTypes.contains(typeOfData)) typeOfData = dataTypes[1]
    if (!sortTypes.contains(typeOfSort)) typeOfSort = sortTypes[0]
    when (typeOfData) {
        dataTypes[0] -> long(typeOfSort)
        dataTypes[1] -> word(typeOfSort)
        dataTypes[2] -> line(typeOfSort)
        
    }
}
fun checkArgs(args: Array<String>, typeOfData: String, typeOfSort: String) {
    if (args.contains(commands[0]) && !dataTypes.contains(typeOfData)) {
        println("No data type defined!")
        exitProcess(0)
    }
    if (args.contains(commands[1]) && !sortTypes.contains(typeOfSort)) {
        println("No sorting type defined!")
        exitProcess(0)
    }
    for (arg in args) {
        if (!allConfigurations.contains(arg)) println("\"$arg\" is not a valid parameter. It will be skipped.")
    }
}
fun long(sortType: String = sortTypes[0]) {
    var numbers = mutableListOf<Int>()
    val numberString = mutableListOf<String>()
    while (scanner.hasNext()) numberString.add(scanner.next())
    for (input in numberString) {
        try {
            input.toInt()
            numbers.add(input.toInt())
        } catch (e: Exception) {
            println("\"$input\" is not a long. It will be skipped.")
        }
    }
    numberString.clear()
    numbers = numbers.sorted().toMutableList()
    numbers.forEach { numberString.add(it.toString()) }
    when (sortType) {
        sortTypes[1] -> sortedByCount(numberString,"numbers", numberString.size)
        sortTypes[0] -> display(numbers.sorted().joinToString(" "), numbers.size, "numbers")
    }
}
fun word(sortType: String = sortTypes[0]) {
    val words = mutableListOf<String>()
    while (scanner.hasNext()) words.add(scanner.next())
    when (sortType) {
        sortTypes[1] -> sortedByCount (words.sorted().toMutableList(), "words", words.size)
        sortTypes[0] -> display(words.sorted().joinToString(" "), words.size, "words")
    }
}
fun line(sortType: String = sortTypes[0]) {
    val lines = mutableListOf<String>()
    while (scanner.hasNextLine()) lines.add(scanner.nextLine())
    when (sortType) {
        sortTypes[1] -> sortedByCount(lines.sorted().toMutableList(), "lines", lines.size)
        sortTypes[0] -> display(lines.sorted().joinToString("\n"), lines.size, "lines")
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

