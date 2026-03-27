package com.example.cross_math_puzzle_compose.ui.screen

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cross_math_puzzle_compose.viewmodel.GameViewModel
import com.example.cross_math_puzzle_compose.ui.component.GridCell
@Composable
fun GameScreen(vm: GameViewModel = viewModel()) {

    val state = vm.puzzleState

    state.grid.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, cell ->
            GridCell(cell) {
                if (cell.editable) {
                    vm.updateCell(rowIndex, colIndex, "6")
                }
            }
        }
    }
}