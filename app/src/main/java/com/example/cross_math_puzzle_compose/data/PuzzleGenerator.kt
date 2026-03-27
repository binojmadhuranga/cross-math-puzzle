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
        // RANDOM HORIZONTAL EQUATION
        // --------------------------

        val a = (1..9).random()
        val b = (1..9).random()
        val result = a + b

        val blankIndex = listOf(0, 2, 4).random()

        val equationValues = mutableListOf(
            a.toString(),
            "+",
            b.toString(),
            "=",
            result.toString()
        )

        val answer = equationValues[blankIndex]

        equationValues[blankIndex] = ""

        for (i in 0..4) {
            mutableGrid[0][i] = Cell(
                row = 0,
                col = i,
                value = equationValues[i],
                editable = i == blankIndex
            )
        }

        // --------------------------
        // RANDOM VERTICAL EQUATION
        // --------------------------

        val x = (1..5).random()
        val y = (1..5).random()
        val verticalResult = x * y

        mutableGrid[0][6] = Cell(0,6,x.toString())
        mutableGrid[1][6] = Cell(1,6,"x")
        mutableGrid[2][6] = Cell(2,6,y.toString())
        mutableGrid[3][6] = Cell(3,6,"=")
        mutableGrid[4][6] = Cell(4,6,verticalResult.toString())

        // --------------------------
        // BLACK CELLS
        // --------------------------

        mutableGrid[1][1] = Cell(1,1,isBlack = true)
        mutableGrid[1][2] = Cell(1,2,isBlack = true)
        mutableGrid[1][3] = Cell(1,3,isBlack = true)
        mutableGrid[1][4] = Cell(1,4,isBlack = true)

        mutableGrid[2][1] = Cell(2,1,isBlack = true)
        mutableGrid[3][1] = Cell(3,1,isBlack = true)
        mutableGrid[4][1] = Cell(4,1,isBlack = true)

        // --------------------------
        // EQUATIONS
        // --------------------------

        val horizontalEquation = Equation(
            cells = listOf(
                Pair(0,0),
                Pair(0,1),
                Pair(0,2),
                Pair(0,3),
                Pair(0,4)
            ),
            expression = "$a+$b=$result",
            answer = answer
        )

        val verticalEquation = Equation(
            cells = listOf(
                Pair(0,6),
                Pair(1,6),
                Pair(2,6),
                Pair(3,6),
                Pair(4,6)
            ),
            expression = "$x x $y = $verticalResult",
            answer = ""
        )

        return PuzzleState(
            grid = mutableGrid,
            equations = listOf(horizontalEquation, verticalEquation)
        )
    }
}