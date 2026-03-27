package com.example.cross_math_puzzle_compose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cross_math_puzzle_compose.ui.component.GridCell
import com.example.cross_math_puzzle_compose.viewmodel.GameViewModel

@Composable
fun GameScreen(vm: GameViewModel = viewModel()) {

    val state = vm.puzzleState

    var showDialog by remember { mutableStateOf(false) }
    var selectedRow by remember { mutableStateOf(0) }
    var selectedCol by remember { mutableStateOf(0) }
    var input by remember { mutableStateOf("") }

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Score: ${state.score}"
            )
        }

        state.grid.forEachIndexed { rowIndex, row ->

            Row {

                row.forEachIndexed { colIndex, cell ->

                    GridCell(cell) {

                        if (cell.editable) {
                            selectedRow = rowIndex
                            selectedCol = colIndex
                            showDialog = true
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },

            confirmButton = {
                Button(
                    onClick = {
                        vm.updateCell(
                            selectedRow,
                            selectedCol,
                            input
                        )
                        input = ""
                        showDialog = false
                    }
                ) {
                    Text("OK")
                }
            },

            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            },

            text = {
                TextField(
                    value = input,
                    onValueChange = {
                        input = it
                    },
                    label = {
                        Text("Enter number")
                    }
                )
            }
        )
    }
}