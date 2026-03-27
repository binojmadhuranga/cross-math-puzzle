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
        val targetCell = puzzleState.grid[row][col]

        if (!targetCell.editable) {
            return
        }

        val nextColor = if (normalizedValue == "6") CellColor.GREEN else CellColor.RED
        val updatedCell = targetCell.copy(
            value = normalizedValue,
            colorState = nextColor
        )

        val updatedRow = puzzleState.grid[row].toMutableList().apply {
            this[col] = updatedCell
        }

        val updatedGrid = puzzleState.grid.toMutableList().apply {
            this[row] = updatedRow
        }

        puzzleState = puzzleState.copy(
            grid = updatedGrid,
            score = if (normalizedValue == "6") 1 else 0
        )
    }

    fun newGame() {
        puzzleState = PuzzleGenerator.generatePuzzle()
    }
}