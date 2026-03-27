package com.example.cross_math_puzzle_compose.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.cross_math_puzzle_compose.data.PuzzleGenerator
import com.example.cross_math_puzzle_compose.model.*

class GameViewModel : ViewModel() {

    var puzzleState by mutableStateOf(PuzzleGenerator.generatePuzzle())
        private set

    fun updateCell(row: Int, col: Int, value: String) {
        val totalEquations = puzzleState.equations.size
        if (totalEquations > 0 && puzzleState.score >= totalEquations) {
            return
        }

        val normalizedValue = value.trim()

        val updatedGrid = puzzleState.grid.map { it.toMutableList() }

        // =========================
        // UPDATE CLICKED CELL
        // =========================

        updatedGrid[row][col] = updatedGrid[row][col].copy(
            value = normalizedValue
        )

        var score = 0

        // =========================
        // CHECK ALL EQUATIONS
        // =========================

        puzzleState.equations.forEach { equation ->

            val cells = equation.cells.map { (r, c) ->
                updatedGrid[r][c]
            }

            val values = cells.map { it.value }

            if (values.none { it.isBlank() }) {

                val isCorrect = checkEquation(values)

                val color = if (isCorrect) {
                    CellColor.GREEN
                } else {
                    CellColor.RED
                }

                equation.cells.forEach { (r, c) ->
                    updatedGrid[r][c] = updatedGrid[r][c].copy(
                        colorState = color
                    )
                }

                if (isCorrect) {
                    score++
                }

            } else {

                equation.cells.forEach { (r, c) ->
                    updatedGrid[r][c] = updatedGrid[r][c].copy(
                        colorState = CellColor.DEFAULT
                    )
                }
            }
        }

        // =========================
        // UPDATE STATE
        // =========================

        puzzleState = puzzleState.copy(
            grid = updatedGrid,
            score = score
        )
    }

    // =========================
    // EQUATION VALIDATION
    // =========================

    private fun checkEquation(values: List<String>): Boolean {

        val a = values[0].toIntOrNull() ?: return false
        val op = values[1]
        val b = values[2].toIntOrNull() ?: return false
        val result = values[4].toIntOrNull() ?: return false

        return when (op) {
            "+" -> a + b == result
            "-" -> a - b == result
            "x" -> a * b == result
            "/" -> b != 0 && a / b == result
            else -> false
        }
    }

    fun newGame() {
        puzzleState = PuzzleGenerator.generatePuzzle()
    }
}