package com.example.cross_math_puzzle_compose.data

import com.example.cross_math_puzzle_compose.model.*

object PuzzleGenerator {

    fun generatePuzzle(): PuzzleState {

        val size = 11

        val grid = List(size) { row ->
            List(size) { col ->
                Cell(row, col)
            }
        }

        val mutableGrid = grid.map { it.toMutableList() }

        mutableGrid[0][0] = Cell(0,0,"3")
        mutableGrid[0][1] = Cell(0,1,"+")
        mutableGrid[0][2] = Cell(0,2,"", editable = true)
        mutableGrid[0][3] = Cell(0,3,"=")
        mutableGrid[0][4] = Cell(0,4,"9")

        val equation = Equation(
            cells = listOf(
                Pair(0,0),
                Pair(0,1),
                Pair(0,2),
                Pair(0,3),
                Pair(0,4)
            ),
            expression = "3+6=9"
        )

        return PuzzleState(
            grid = mutableGrid,
            equations = listOf(equation)
        )
    }
}