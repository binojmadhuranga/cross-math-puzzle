package com.example.cross_math_puzzle_compose.model

data class Cell(
    val row: Int,
    val col: Int,
    var value: String = "",
    val editable: Boolean = false,
    var colorState: CellColor = CellColor.DEFAULT
)

enum class CellColor {
    DEFAULT,
    GREEN,
    RED,
    BLACK
}