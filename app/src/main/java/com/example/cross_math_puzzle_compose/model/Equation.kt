package com.example.cross_math_puzzle_compose.model

data class Equation(
    val cells: List<Pair<Int, Int>>,
    val expression: String,
    val answer: String
)