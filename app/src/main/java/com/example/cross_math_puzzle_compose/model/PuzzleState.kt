package com.example.cross_math_puzzle_compose.model

data class PuzzleState(
    val grid: List<List<Cell>>,
    val equations: List<Equation>,
    val score: Int = 0
)