package com.example.cross_math_puzzle_compose.data

import com.example.cross_math_puzzle_compose.model.*

object PuzzleGenerator {

    fun generatePuzzle(): PuzzleState {

        val size = 11

        val mutableGrid = MutableList(size) { row ->
            MutableList(size) { col ->
                Cell(row, col, isBlack = true)
            }
        }

        val equations = mutableListOf<Equation>()

        // -------------------------
        // 5 HORIZONTAL EQUATIONS
        // -------------------------

        placeHorizontal(mutableGrid, equations, 0, 0)
        placeHorizontal(mutableGrid, equations, 2, 0)
        placeHorizontal(mutableGrid, equations, 4, 0)
        placeHorizontal(mutableGrid, equations, 6, 0)
        placeHorizontal(mutableGrid, equations, 8, 0)

        // -------------------------
        // 4 VERTICAL EQUATIONS
        // -------------------------

        placeVertical(mutableGrid, equations, 0, 7)
        placeVertical(mutableGrid, equations, 0, 9)
        placeVertical(mutableGrid, equations, 4, 5)
        placeVertical(mutableGrid, equations, 4, 10)

        return PuzzleState(
            grid = mutableGrid,
            equations = equations
        )
    }

    // ===================================================
    // HORIZONTAL EQUATION
    // ===================================================

    private fun placeHorizontal(
        grid: MutableList<MutableList<Cell>>,
        equations: MutableList<Equation>,
        row: Int,
        startCol: Int
    ) {
        val a = (1..9).random()
        val b = (1..9).random()
        val result = a + b

        val values = mutableListOf(
            a.toString(),
            "+",
            b.toString(),
            "=",
            result.toString()
        )

        val blankIndex = listOf(0, 2, 4).random()
        val answer = values[blankIndex]

        values[blankIndex] = ""

        for (i in values.indices) {
            val col = startCol + i

            grid[row][col] = Cell(
                row = row,
                col = col,
                value = values[i],
                editable = i == blankIndex,
                isBlack = false
            )
        }

        equations.add(
            Equation(
                cells = List(5) { Pair(row, startCol + it) },
                expression = "$a+$b=$result",
                answer = answer
            )
        )
    }

    // ===================================================
    // VERTICAL EQUATION
    // ===================================================

    private fun placeVertical(
        grid: MutableList<MutableList<Cell>>,
        equations: MutableList<Equation>,
        startRow: Int,
        col: Int
    ) {
        val a = (1..5).random()
        val b = (1..5).random()
        val result = a * b

        val values = mutableListOf(
            a.toString(),
            "x",
            b.toString(),
            "=",
            result.toString()
        )

        val blankIndex = listOf(0, 2, 4).random()
        val answer = values[blankIndex]

        values[blankIndex] = ""

        for (i in values.indices) {

            val row = startRow + i
            val existing = grid[row][col]

            if (
                existing.isBlack ||
                existing.value == values[i] ||
                existing.value == ""
            ) {
                grid[row][col] = Cell(
                    row = row,
                    col = col,
                    value = values[i],
                    editable = i == blankIndex,
                    isBlack = false
                )
            }
        }

        equations.add(
            Equation(
                cells = List(5) { Pair(startRow + it, col) },
                expression = "$a x $b = $result",
                answer = answer
            )
        )
    }
}