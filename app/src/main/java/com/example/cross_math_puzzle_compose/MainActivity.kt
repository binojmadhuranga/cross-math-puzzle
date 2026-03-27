package com.example.cross_math_puzzle_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cross_math_puzzle_compose.ui.screen.GameScreen
import com.example.cross_math_puzzle_compose.ui.theme.CrossmathpuzzlecomposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CrossmathpuzzlecomposeTheme {

                Surface(modifier = Modifier.fillMaxSize()) {

                    var currentScreen by rememberSaveable {
                        mutableStateOf("home")
                    }

                    var showAboutDialog by rememberSaveable {
                        mutableStateOf(false)
                    }

                    when (currentScreen) {

                        "home" -> {
                            HomeScreen(
                                onNewGame = {
                                    currentScreen = "game"
                                },
                                onAdvanced = {
                                    currentScreen = "game"
                                },
                                onAbout = {
                                    showAboutDialog = true
                                }
                            )
                        }

                        "game" -> {
                            GameScreen()
                        }
                    }

                    if (showAboutDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                showAboutDialog = false
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        showAboutDialog = false
                                    }
                                ) {
                                    Text("OK")
                                }
                            },
                            title = {
                                Text("About")
                            },
                            text = {
                                Text(
                                    "Name: Your Name\nStudent ID: Your ID"
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun HomeScreen(
        onNewGame: () -> Unit,
        onAdvanced: () -> Unit,
        onAbout: () -> Unit
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Cross Math Puzzle",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = onNewGame) {
                Text("New Game")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onAdvanced) {
                Text("Advanced Level")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onAbout) {
                Text("About")
            }
        }
    }
}