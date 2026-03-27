package com.example.cross_math_puzzle_compose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cross_math_puzzle_compose.model.*

@Composable
fun GridCell(cell: Cell, onClick: () -> Unit) {

    val color = when(cell.colorState) {
        CellColor.GREEN -> Color.Green
        CellColor.RED -> Color.Red
        CellColor.BLACK -> Color.Black
        else -> Color.White
    }

    Box(
        modifier = Modifier
            .size(40.dp)
            .background(color)
            .clickable { onClick() }
    ) {
        Text(cell.value)
    }
}