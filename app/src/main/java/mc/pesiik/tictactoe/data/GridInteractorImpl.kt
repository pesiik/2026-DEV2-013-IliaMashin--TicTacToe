package mc.pesiik.tictactoe.data

import mc.pesiik.tictactoe.domain.Cell
import mc.pesiik.tictactoe.domain.Grid
import mc.pesiik.tictactoe.domain.Player
import mc.pesiik.tictactoe.domain.Status
import mc.pesiik.tictactoe.interactor.GridInteractor
import mc.pesiik.tictactoe.repository.GridRepository
import javax.inject.Inject

class GridInteractorImpl @Inject constructor(
    private val gridRepository: GridRepository
) : GridInteractor {

    override fun cross(row: Int, col: Int): Grid {
        val cells = gridRepository.getGrid().cells
        val updatedCells = update(cells, row, col, isCross = true)
        val player = if (isCellNotEmpty(cells, row, col)) Player.CROSS else Player.ZERO
        val status = checkForWinner(updatedCells, Player.CROSS)
        return gridRepository.updateGrid(
            cells = updatedCells,
            player = player,
            status = status,
        )
    }

    override fun zero(row: Int, col: Int): Grid {
        val cells = gridRepository.getGrid().cells
        val updatedCells = update(cells, row, col, isCross = false)
        val player = if (isCellNotEmpty(cells, row, col)) Player.ZERO else Player.CROSS
        val status = checkForWinner(updatedCells, Player.ZERO)
        return gridRepository.updateGrid(
            cells = updatedCells,
            player = player,
            status = status,
        )
    }

    override fun reset(): Grid {
        return gridRepository.updateGrid(
            cells = buildList {
                repeat(3) {
                    add(List(3) { Cell.NONE })
                }
            },
            player = Player.CROSS
        )
    }

    private fun update(cells: List<List<Cell>>, row: Int, col: Int, isCross: Boolean): List<List<Cell>> {
        if (isCellNotEmpty(cells, row, col)) {
            return cells
        }
        return cells.mapIndexed { rIndex, rowList ->
            rowList.mapIndexed { cIndex, cell ->
                if (rIndex == row && cIndex == col) {
                    if (isCross) Cell.CROSS else Cell.ZERO
                } else {
                    cell
                }
            }
        }
    }

    private fun isCellNotEmpty(cells: List<List<Cell>>, row: Int, col: Int): Boolean {
        return cells[row][col] != Cell.NONE
    }

    private fun checkForWinner(cells: List<List<Cell>>, player: Player): Status {
        val winningLines = listOf(
            // Rows
            listOf(Pair(0, 0), Pair(0, 1), Pair(0, 2)),
            listOf(Pair(1, 0), Pair(1, 1), Pair(1, 2)),
            listOf(Pair(2, 0), Pair(2, 1), Pair(2, 2)),
            // Columns
            listOf(Pair(0, 0), Pair(1, 0), Pair(2, 0)),
            listOf(Pair(0, 1), Pair(1, 1), Pair(2, 1)),
            listOf(Pair(0, 2), Pair(1, 2), Pair(2, 2)),
            // Diagonals
            listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2)),
            listOf(Pair(0, 2), Pair(1, 1), Pair(2, 0))
        )

        winningLines.forEach { line ->
            val cellValues = line.map { (row, col) -> cells[row][col] }
            val firstCell = cellValues[0]
            if (firstCell != Cell.NONE && cellValues.all { it == firstCell }) {
                return Status.Winner(player, line)
            }
        }

        return if (cells.all { row -> row.all { it != Cell.NONE } }) {
            Status.Draw
        } else {
            Status.Active
        }
    }
}