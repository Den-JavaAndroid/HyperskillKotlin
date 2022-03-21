package tictactoe

import java.util.InputMismatchException
import java.util.Scanner
import kotlin.math.abs

const val EMPTY_CELL = '_'
const val X_CELL = 'X'
const val O_CELL = 'O'
val scanner = Scanner(System.`in`)

enum class GameResult(val result: String) {
    IMPOSSIBLE("Impossible"),
    O_WINS("O wins"),
    X_WINS("X wins"),
    DRAW("Draw"),
    NOT_FINISHED("Game not finished")
}

fun main() {

    val board = mutableListOf(
        mutableListOf(EMPTY_CELL, EMPTY_CELL, EMPTY_CELL),
        mutableListOf(EMPTY_CELL, EMPTY_CELL, EMPTY_CELL),
        mutableListOf(EMPTY_CELL, EMPTY_CELL, EMPTY_CELL),
    )
    val game = Game(board)
    var isFinishedGame = false
    while (!isFinishedGame) {
        printBoard(board)
        var stepResult = game.makeStep(X_CELL)
        if (stepResult.first) {
            printBoard(board)
            println(stepResult.second.result)
            isFinishedGame = true
            continue
        }
        printBoard(board)
        stepResult = game.makeStep(O_CELL)
        if (stepResult.first) {
            printBoard(board)
            println(stepResult.second.result)
            isFinishedGame = true
            continue
        }
    }
}

class Game(var board: MutableList<MutableList<Char>>) {
    val endGameResults = mutableListOf(GameResult.DRAW, GameResult.X_WINS, GameResult.O_WINS)
    var isFinished = false
    fun makeStep(player: Char): Pair<Boolean, GameResult> {
        inputCoordinatesAndUpdateCell(board, player)
        var analyzeGameResult = analyzeGameResult(board)
        if (analyzeGameResult in endGameResults) {
            isFinished = true
        }
        return Pair(isFinished, analyzeGameResult)
    }


}

private fun inputCoordinatesAndUpdateCell(board: MutableList<MutableList<Char>>, player: Char) {
    var x = -1
    var y = -1
    var isCorrect = false
    while (!isCorrect) {
        println("Enter the coordinates:")
        try {
            x = scanner.nextInt()
            y = scanner.nextInt()
        } catch (e: InputMismatchException) {
            println("You should enter numbers!")
            continue
        }
        if (x in 1..3 && y in 1..3) {
        } else {
            println("Coordinates should be from 1 to 3!")
            continue
        }
        val selectedCell = board[x - 1][y - 1];
        if (selectedCell != EMPTY_CELL) {
            println("This cell is occupied! Choose another one!")
            continue
        }
        isCorrect = true
    }
    board[x - 1][y - 1] = player
}

fun analyzeGameResult(board: MutableList<MutableList<Char>>): GameResult {
    var isWinX = false
    var isWinO = false
    var countEmptyCells = 0
    var countX = 0;
    var countO = 0
    for (i in 0..7) {
        var line = when (i) {
            0 -> "${board[i][0]}${board[i][1]}${board[i][2]}"
            1 -> "${board[i][0]}${board[i][1]}${board[i][2]}"
            2 -> "${board[i][0]}${board[i][1]}${board[i][2]}"
            3 -> "${board[0][0]}${board[1][0]}${board[2][0]}"
            4 -> "${board[0][1]}${board[1][1]}${board[2][1]}"
            5 -> "${board[0][2]}${board[1][2]}${board[2][2]}"
            6 -> "${board[0][0]}${board[1][1]}${board[2][2]}"
            7 -> "${board[2][0]}${board[1][1]}${board[0][2]}"
            else -> {
                ""
            }
        }
        if (line == "XXX") {
            isWinX = true
        }
        if (line == "OOO") {
            isWinO = true
        }
    }
    for (i in 0 until board.size) {
        countEmptyCells += board[i].count { it == EMPTY_CELL }
        countO += board[i].count { it == O_CELL }
        countX += board[i].count { it == X_CELL }
    }

    if (isWinO && isWinX) return GameResult.IMPOSSIBLE
    if (abs(countO - countX) > 1) return GameResult.IMPOSSIBLE
    if (isWinO) return GameResult.O_WINS
    if (isWinX) return GameResult.X_WINS
    if (countEmptyCells == 0) return GameResult.DRAW
    return GameResult.NOT_FINISHED
}

fun printBoard(board: MutableList<MutableList<Char>>) {
    println("---------")
    for (row in board) {
        println("| ${row.joinToString(" ")} |")
    }
    println("---------")
}