package converter

fun main() {
    var isStopped = false
    while (!isStopped) {
        println("Do you want to convert /from decimal or /to decimal? (To quit type /exit)")
        when (readln()) {
            "/from" -> convertFromDecimal()
            "/to" -> convertToDecimal()
            "/exit" -> isStopped = true
        }
    }


}

fun convertFromDecimal() {
    println("Enter a number in decimal system:")
    val decimalNumber = readln().toInt()
    println("Enter the target base:")
    val result = when (readln().toInt()) {
        2 -> Integer.toBinaryString(decimalNumber)
        8 -> Integer.toOctalString(decimalNumber)
        16 -> Integer.toHexString(decimalNumber)
        else -> "incorrect base"
    }
    println("Conversion result: $result")
}

fun convertToDecimal() {
    println("Enter source number:")
    val sourceNumber = readln()
    println("Enter source base:")
    val result = when (readln().toInt()) {
        2 -> Integer.parseInt(sourceNumber, 2)
        8 -> Integer.parseInt(sourceNumber, 8)
        16 -> Integer.parseInt(sourceNumber, 16)
        else -> "incorrect base"
    }
    println("Conversion to decimal result: $result")
}