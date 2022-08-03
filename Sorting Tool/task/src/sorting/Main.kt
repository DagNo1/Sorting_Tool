package sorting

import java.util.Scanner
import kotlin.math.roundToInt
import kotlin.system.exitProcess
import java.io.File

val scanner  = Scanner(System.`in`)
val commands =  listOf("-dataType", "-sortingType", "-inputFile", "-outputFile")
val dataTypes = listOf("long", "word", "line")
val sortTypes = listOf("natural", "byCount")
val allConfigurations = commands + dataTypes + sortTypes

fun main(args: Array<String>) {
    var typeOfData = try { args[args.indexOf(commands[0]) + 1] } catch (e: ArrayIndexOutOfBoundsException) {""}
    var typeOfSort = try { args[args.indexOf(commands[1]) + 1] } catch (e: ArrayIndexOutOfBoundsException) {""}
    val inputFileName = if (args.contains(commands[2])) {
        try {
            args[args.indexOf(commands[2]) + 1]
        } catch (e: Exception) {
            ""
        }
    } else ""
    val outputFileName = if (args.contains(commands[3])) {
        try {
            args[args.indexOf(commands[3]) + 1]
        } catch (e: Exception) {
            ""
        }
    } else ""
    checkArgs(args, typeOfData, typeOfSort)
    if (!dataTypes.contains(typeOfData)) typeOfData = dataTypes[1]
    if (!sortTypes.contains(typeOfSort)) typeOfSort = sortTypes[0]

    when (typeOfData) {
        dataTypes[0] -> long(typeOfSort,inputFileName, outputFileName)
        dataTypes[1] -> word(typeOfSort,inputFileName, outputFileName)
        dataTypes[2] -> line(typeOfSort,inputFileName, outputFileName)
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
fun long(sortType: String = sortTypes[0], inputFileName: String, outputFileName: String) {
    var numbers = mutableListOf<Int>()
    var numberString = mutableListOf<String>()
    if (inputFileName == "") while (scanner.hasNext()) numberString.add(scanner.next())
    else numberString = File(inputFileName).readText().split(Regex("\\s+")).toMutableList()
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
        sortTypes[1] -> sortedByCount(numberString,"numbers", numberString.size, outputFileName)
        sortTypes[0] -> display(numbers.sorted().joinToString(" "), numbers.size, "numbers", outputFileName)
    }
}
fun word(sortType: String = sortTypes[0], inputFileName: String, outputFileName: String) {
    var words = mutableListOf<String>()
    if (inputFileName == "") while (scanner.hasNext()) words.add(scanner.next())
    else words = File(inputFileName).readText().split(Regex("\\s+")).toMutableList()
    when (sortType) {
        sortTypes[1] -> sortedByCount (words.sorted().toMutableList(), "words", words.size, outputFileName)
        sortTypes[0] -> display(words.sorted().joinToString(" "), words.size, "words", outputFileName)
    }
}
fun line(sortType: String = sortTypes[0], inputFileName: String, outputFileName: String) {
    var lines = mutableListOf<String>()
    if (inputFileName == "") while (scanner.hasNextLine()) lines.add(scanner.nextLine())
    else lines = File(inputFileName).readLines().toMutableList()
    when (sortType) {
        sortTypes[1] -> sortedByCount(lines.sorted().toMutableList(), "lines", lines.size, outputFileName)
        sortTypes[0] -> display(lines.sorted().joinToString("\n"), lines.size, "lines", outputFileName)
    }
}
fun sortedByCount(keys: MutableList<String>, total: String, size: Int, outputFileName: String) {
    val countMap = keys.groupingBy { it }.eachCount()
    val sortedMap = countMap.toList().sortedBy { (_, value) -> value}.toMap().toMutableMap()
    println("Total $total: $size.")
    for ((k, v) in sortedMap) print("$k: $v time(s), ${(v * 100.0 / size).roundToInt()}%\n")
    if (outputFileName != ""){
        val outputFile = File(outputFileName)
        outputFile.writeText("Total $total: $size.")
        for ((k, v) in sortedMap) outputFile.writeText("$k: $v time(s), ${(v * 100.0 / size).roundToInt()}%\n")
    }
}
fun display(sortedData: String, size: Int, dataType: String, outputFileName: String) {
    println("Total $dataType: $size.")
    print("Sorted data: ")
    if (dataType != "lines")  println(sortedData) else println("\n$sortedData")
    if (outputFileName != ""){
        val outputFile = File(outputFileName)
        outputFile.writeText("Total $dataType: $size.")
        outputFile.writeText("Sorted data: ")
        outputFile.writeText(if (dataType != "lines") sortedData else "\n$sortedData")
    }
}

