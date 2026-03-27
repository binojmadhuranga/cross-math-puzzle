package com.example.cross_math_puzzle_compose.data

import com.example.cross_math_puzzle_compose.model.*

object PuzzleGenerator {

    fun generatePuzzle(): PuzzleState {

        val rows = (11..20).random()
        val cols = (11..20).random()

        val equationCount = when {
            rows <= 13 -> 9
            rows <= 16 -> 11
            rows <= 18 -> 13
            else -> 15
        }

        val mutableGrid = MutableList(rows) { row ->
            MutableList(cols) { col ->
                Cell(row, col, isBlack = true)
            }
        }

        val equations = mutableListOf<Equation>()

        var horizontalCount = equationCount / 2
        var verticalCount = equationCount - horizontalCount

        // =========================
        // HORIZONTAL EQUATIONS
        // =========================

        repeat(horizontalCount) {

            var placed = false

            while (!placed) {

                val row = (0 until rows).random()
                val col = (0 until cols - 5).random()

                if (canPlaceHorizontal(mutableGrid, row, col)) {
                    placeHorizontal(mutableGrid, equations, row, col)
                    placed = true
                }
            }
        }

        // =========================
        // VERTICAL EQUATIONS
        // =========================

        repeat(verticalCount) {

            var placed = false

            while (!placed) {

                val row = (0 until rows - 5).random()
                val col = (0 until cols).random()

                if (canPlaceVertical(mutableGrid, row, col)) {
                    placeVertical(mutableGrid, equations, row, col)
                    placed = true
                }
            }
        }

        return PuzzleState(
            grid = mutableGrid,
            equations = equations
        )
    }

    // =========================
    // CHECK HORIZONTAL SPACE
    // =========================

    private fun canPlaceHorizontal(
        grid: MutableList<MutableList<Cell>>,
        row: Int,
        startCol: Int
    ): Boolean {
        for (i in 0 until 5) {
            if (!grid[row][startCol + i].isBlack) return false
        }
        return true
    }

    // =========================
    // CHECK VERTICAL SPACE
    // =========================

    private fun canPlaceVertical(
        grid: MutableList<MutableList<Cell>>,
        startRow: Int,
        col: Int
    ): Boolean {
        for (i in 0 until 5) {
            if (!grid[startRow + i][col].isBlack) return false
        }
        return true
    }

    // =========================
    // PLACE HORIZONTAL
    // =========================

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

    // =========================
    // PLACE VERTICAL
    // =========================

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
                cells = List(5) { Pair(startRow + it, col) },
                expression = "$a x $b = $result",
                answer = answer
            )
        )
    }
}