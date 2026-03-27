package com.example.cross_math_puzzle_compose.data

import com.example.cross_math_puzzle_compose.model.*

object PuzzleGenerator {

    fun generatePuzzle(): PuzzleState {

        val size = 11

        val grid = List(size) { row ->
            List(size) { col ->
                Cell(row, col, isBlack = false)
            }
        }

        val mutableGrid = grid.map { it.toMutableList() }

        // --------------------------
        // Horizontal Equation: 3 + _ = 9
        // answer = 6
        // --------------------------

        mutableGrid[0][0] = Cell(0,0,"3")
        mutableGrid[0][1] = Cell(0,1,"+")
        mutableGrid[0][2] = Cell(0,2,"", editable = true)
        mutableGrid[0][3] = Cell(0,3,"=")
        mutableGrid[0][4] = Cell(0,4,"9")

        // --------------------------
        // Vertical Equation: _ x 2 = 6
        // answer = 3
        // crossing at [0][0]
        // --------------------------

        mutableGrid[1][0] = Cell(1,0,"x")
        mutableGrid[2][0] = Cell(2,0,"2")
        mutableGrid[3][0] = Cell(3,0,"=")
        mutableGrid[4][0] = Cell(4,0,"6")

        // --------------------------
        // Black blocked cells
        // --------------------------

        mutableGrid[1][1] = Cell(1,1,isBlack = true)
        mutableGrid[1][2] = Cell(1,2,isBlack = true)
        mutableGrid[1][3] = Cell(1,3,isBlack = true)
        mutableGrid[1][4] = Cell(1,4,isBlack = true)

        mutableGrid[2][1] = Cell(2,1,isBlack = true)
        mutableGrid[3][1] = Cell(3,1,isBlack = true)
        mutableGrid[4][1] = Cell(4,1,isBlack = true)

        // --------------------------
        // Equations list
        // --------------------------

        val horizontalEquation = Equation(
            cells = listOf(
                Pair(0,0),
                Pair(0,1),
                Pair(0,2),
                Pair(0,3),
                Pair(0,4)
            ),
            expression = "3+6=9"
        )

        val verticalEquation = Equation(
            cells = listOf(
                Pair(0,0),
                Pair(1,0),
                Pair(2,0),
                Pair(3,0),
                Pair(4,0)
            ),
            expression = "3x2=6"
        )

        return PuzzleState(
            grid = mutableGrid,
            equations = listOf(horizontalEquation, verticalEquation)
        )
    }
}