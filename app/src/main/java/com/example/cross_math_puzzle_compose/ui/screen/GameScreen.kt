package com.example.cross_math_puzzle_compose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cross_math_puzzle_compose.viewmodel.GameViewModel
import com.example.cross_math_puzzle_compose.ui.component.GridCell

@Composable
fun GameScreen(vm: GameViewModel = viewModel()) {
    val state = vm.puzzleState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Game",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            state.grid.forEachIndexed { rowIndex, row ->
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    row.forEachIndexed { colIndex, cell ->
                        GridCell(cell) {
                            if (cell.editable) {
                                vm.updateCell(rowIndex, colIndex, "6")
                            }
                        }
                    }
                }
            }
        }
    }
}