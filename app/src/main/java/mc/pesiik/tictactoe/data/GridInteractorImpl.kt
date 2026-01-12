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
        // Check rows and columns
        for (i in 0..2) {
            if ((cells[i][0] != Cell.NONE && cells[i][0] == cells[i][1] && cells[i][1] == cells[i][2]) ||
                (cells[0][i] != Cell.NONE && cells[0][i] == cells[1][i] && cells[1][i] == cells[2][i])
            ) {
                return Status.Winner(player)
            }
        }
        // Check diagonals
        if ((cells[0][0] != Cell.NONE && cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) ||
            (cells[0][2] != Cell.NONE && cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0])
        ) {
            return Status.Winner(player)
        }
        val isDraw = checkForDraw(cells)
        if (isDraw) {
            return Status.Draw
        }
        return Status.Active
    }

    private fun checkForDraw(cells: List<List<Cell>>): Boolean {
        return cells.all { row -> row.all { cell -> cell != Cell.NONE } }
    }
}