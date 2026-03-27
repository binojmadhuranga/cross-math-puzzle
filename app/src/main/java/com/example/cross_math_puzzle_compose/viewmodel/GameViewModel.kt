package com.example.cross_math_puzzle_compose.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.cross_math_puzzle_compose.data.PuzzleGenerator
import com.example.cross_math_puzzle_compose.model.*

class GameViewModel : ViewModel() {

    var puzzleState by mutableStateOf(PuzzleGenerator.generatePuzzle())
        private set

    fun updateCell(row: Int, col: Int, value: String) {

        val normalizedValue = value.trim()

        val updatedGrid = puzzleState.grid.map { it.toMutableList() }

        updatedGrid[row][col] = updatedGrid[row][col].copy(
            value = normalizedValue
        )

        val equation = puzzleState.equations.find {
            it.cells.contains(Pair(row, col))
        }

        if (equation != null) {

            val isCorrect = normalizedValue == equation.answer

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

            val newScore = if (isCorrect) 1 else 0

            puzzleState = puzzleState.copy(
                grid = updatedGrid,
                score = newScore
            )
        }
    }

    fun newGame() {
        puzzleState = PuzzleGenerator.generatePuzzle()
    }
}