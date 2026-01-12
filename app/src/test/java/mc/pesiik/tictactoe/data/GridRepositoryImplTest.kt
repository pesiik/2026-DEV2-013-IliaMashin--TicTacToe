package mc.pesiik.tictactoe.data

import mc.pesiik.tictactoe.domain.Cell
import mc.pesiik.tictactoe.domain.Grid
import mc.pesiik.tictactoe.domain.Player
import mc.pesiik.tictactoe.domain.Status
import org.junit.Assert
import org.junit.Test

class GridRepositoryImplTest {

    private val gridRepository = GridRepositoryImpl()

    @Test
    fun `WHEN create repository THEN return grid`() {
        val expected = Grid(
            cells = listOf(
                listOf(Cell.NONE, Cell.NONE, Cell.NONE),
                listOf(Cell.NONE, Cell.NONE, Cell.NONE),
                listOf(Cell.NONE, Cell.NONE, Cell.NONE)
            ),
            currentPlayer = Player.CROSS,
            status = Status.Active,
        )
        val actual = gridRepository.getGrid()
        Assert.assertEquals(
            expected,
            actual
        )
    }

    @Test
    fun `WHEN update repository THEN return updated grid`() {
        val expected = Grid(
            cells = listOf(
                listOf(Cell.CROSS, Cell.NONE, Cell.NONE),
                listOf(Cell.NONE, Cell.ZERO, Cell.NONE),
                listOf(Cell.NONE, Cell.NONE, Cell.CROSS)
            ),
            currentPlayer = Player.ZERO,
            status = Status.Winner(Player.ZERO, winnerLine = emptyList())
        )
        val actual = gridRepository.updateGrid(
            cells = listOf(
                listOf(Cell.CROSS, Cell.NONE, Cell.NONE),
                listOf(Cell.NONE, Cell.ZERO, Cell.NONE),
                listOf(Cell.NONE, Cell.NONE, Cell.CROSS)
            ),
            player = Player.ZERO,
            status = Status.Winner(Player.ZERO, winnerLine = emptyList()),
        )
        Assert.assertEquals(
            expected,
            actual,
        )
    }
}