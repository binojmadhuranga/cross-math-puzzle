package com.example.cross_math_puzzle_compose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cross_math_puzzle_compose.model.Cell
import com.example.cross_math_puzzle_compose.model.CellColor

@Composable
fun GridCell(
    cell: Cell,
    onClick: () -> Unit
) {

    val backgroundColor = when {
        cell.isBlack -> Color.Black

        cell.colorState == CellColor.GREEN -> Color.Green

        cell.colorState == CellColor.RED -> Color.Red

        else -> Color.White
    }

    Box(
        modifier = Modifier
            .size(40.dp)
            .background(backgroundColor)
            .border(1.dp, Color.Black)
            .clickable(enabled = cell.editable) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        if (!cell.isBlack) {
            Text(
                text = cell.value,
                color = Color.Black
            )
        }
    }
}