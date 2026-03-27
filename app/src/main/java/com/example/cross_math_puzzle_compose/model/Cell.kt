package com.example.cross_math_puzzle_compose.model

data class Cell(
    val row: Int,
    val col: Int,
    val value: String = "",
    val editable: Boolean = false,
    val isBlack: Boolean = false,
    val colorState: CellColor = CellColor.DEFAULT
)

enum class CellColor {
    DEFAULT,
    GREEN,
    RED,
    BLACK
}