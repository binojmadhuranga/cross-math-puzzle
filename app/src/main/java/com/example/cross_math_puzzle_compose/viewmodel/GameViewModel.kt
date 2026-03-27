package com.example.cross_math_puzzle_compose.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.cross_math_puzzle_compose.data.PuzzleGenerator
import com.example.cross_math_puzzle_compose.model.*

class GameViewModel : ViewModel() {

    var puzzleState by mutableStateOf(PuzzleGenerator.generatePuzzle())
        private set

    fun updateCell(row: Int, col: Int, value: String) {
        val updatedGrid = puzzleState.grid.map { it.toMutableList() }

        updatedGrid[row][col].value = value

        if (value == "6") {
            updatedGrid[row][col].colorState = CellColor.GREEN
        } else {
            updatedGrid[row][col].colorState = CellColor.RED
        }

        puzzleState = puzzleState.copy(
            grid = updatedGrid,
            score = if (value == "6") puzzleState.score + 1 else puzzleState.score
        )
    }

    fun newGame() {
        puzzleState = PuzzleGenerator.generatePuzzle()
    }
}