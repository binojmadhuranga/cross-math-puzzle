package com.example.cross_math_puzzle_compose.ui.screen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cross_math_puzzle_compose.ui.component.GridCell
import com.example.cross_math_puzzle_compose.viewmodel.GameViewModel

@Composable
fun GameScreen(
    onGoHome: () -> Unit,
    vm: GameViewModel = viewModel()
) {

    val state = vm.puzzleState

    var showDialog by remember { mutableStateOf(false) }
    var selectedRow by remember { mutableStateOf(0) }
    var selectedCol by remember { mutableStateOf(0) }
    var input by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        // =========================
        // TOP CONTENT
        // =========================

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // SCORE
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Score: ${state.score} / 9",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // GRID
            Box(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                Column {

                    state.grid.forEachIndexed { rowIndex, row ->

                        Row {

                            row.forEachIndexed { colIndex, cell ->

                                GridCell(cell) {

                                    if (cell.editable) {
                                        selectedRow = rowIndex
                                        selectedCol = colIndex
                                        input = cell.value
                                        showDialog = true
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // =========================
        // GO HOME BUTTON
        // =========================

        Button(
            onClick = {
                onGoHome()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Go To Home")
        }
    }

    // =========================
    // INPUT DIALOG
    // =========================

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },

            confirmButton = {
                Button(
                    onClick = {
                        if (input.isNotBlank()) {
                            vm.updateCell(
                                selectedRow,
                                selectedCol,
                                input
                            )
                        }

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

            title = {
                Text("Enter Number")
            },

            text = {
                TextField(
                    value = input,
                    onValueChange = {
                        if (it.length <= 2) {
                            input = it.filter { ch -> ch.isDigit() }
                        }
                    },
                    label = {
                        Text("Number")
                    }
                )
            }
        )
    }
}