package converter

import java.math.BigInteger
import java.util.Scanner

const val BACK_COMMAND = "/back"
const val EXIT_COMMAND = "/exit"
val scanner = Scanner(System.`in`)
fun main() {
    var isStopped = false
    while (!isStopped) {
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        val sourceBase = scanner.next()
        if (sourceBase == EXIT_COMMAND) {
            isStopped = true
            continue
        }
        val targetBase = scanner.nextInt()
        var number = ""
        while (number != BACK_COMMAND) {
            println("Enter number in base $sourceBase to convert to base $targetBase (To go back type /back)")
            number = scanner.next()
            if (number == BACK_COMMAND) continue
            println("Conversion result: ${BigInteger(number, sourceBase.toInt()).toString(targetBase)}")
        }
    }
}
