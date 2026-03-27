package com.example.cross_math_puzzle_compose.ui.screen

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onNewGame: () -> Unit,
    onAdvanced: () -> Unit,
    onAbout: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = onNewGame) {
            Text("New Game")
        }

        Button(onClick = onAdvanced) {
            Text("Advanced Level")
        }

        Button(onClick = onAbout) {
            Text("About")
        }
    }
}