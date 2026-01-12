package mc.pesiik.tictactoe.data

import io.mockk.every
import io.mockk.mockk
import mc.pesiik.tictactoe.domain.Cell
import mc.pesiik.tictactoe.domain.Grid
import mc.pesiik.tictactoe.domain.Player
import mc.pesiik.tictactoe.domain.Status
import mc.pesiik.tictactoe.repository.GridRepository
import org.junit.Assert
import org.junit.Test

class GridInteractorImplTest {

    private val gridRepository: GridRepository = mockk()
    private val interactor = GridInteractorImpl(gridRepository)

    @Test
    fun `GIVEN no winner WHEN cross THEN get correct grid`() {
        val grid = Grid(
            cells = listOf(
                listOf(
                    Cell.CROSS, Cell.NONE, Cell.NONE
                ),
                listOf(
                    Cell.NONE, Cell.NONE, Cell.NONE
                ),
                listOf(
                    Cell.ZERO, Cell.NONE, Cell.NONE
                )
            ),
            currentPlayer = Player.CROSS,
            status = Status.Active,
        )
        val updatedCells = listOf(
            listOf(
                Cell.CROSS, Cell.NONE, Cell.NONE
            ),
            listOf(
                Cell.NONE, Cell.CROSS, Cell.NONE
            ),
            listOf(
                Cell.ZERO, Cell.NONE, Cell.NONE
            )
        )
        val expected: Grid = mockk()
        every { gridRepository.getGrid() } returns grid
        every { gridRepository.updateGrid(cells = updatedCells, player = Player.ZERO) } returns expected

        val actual = interactor.cross(row = 1, col = 1)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN has diagonal winner WHEN cross THEN get correct grid`() {
        val grid = Grid(
            cells = listOf(
                listOf(
                    Cell.CROSS, Cell.NONE, Cell.NONE
                ),
                listOf(
                    Cell.NONE, Cell.CROSS, Cell.NONE
                ),
                listOf(
                    Cell.ZERO, Cell.NONE, Cell.NONE
                )
            ),
            currentPlayer = Player.CROSS,
            status = Status.Active,
        )
        val updatedCells = listOf(
            listOf(
                Cell.CROSS, Cell.NONE, Cell.NONE
            ),
            listOf(
                Cell.NONE, Cell.CROSS, Cell.NONE
            ),
            listOf(
                Cell.ZERO, Cell.NONE, Cell.CROSS
            )
        )
        val expected: Grid = mockk()
        val status = Status.Winner(player = Player.CROSS)
        every { gridRepository.getGrid() } returns grid
        every { gridRepository.updateGrid(cells = updatedCells, player = Player.ZERO, status = status) } returns expected

        val actual = interactor.cross(row = 2, col = 2)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN has vertical winner WHEN cross THEN get correct grid`() {
        val grid = Grid(
            cells = listOf(
                listOf(
                    Cell.CROSS, Cell.NONE, Cell.NONE
                ),
                listOf(
                    Cell.CROSS, Cell.ZERO, Cell.NONE
                ),
                listOf(
                    Cell.NONE, Cell.ZERO, Cell.NONE
                )
            ),
            currentPlayer = Player.CROSS,
            status = Status.Active,
        )
        val updatedCells = listOf(
            listOf(
                Cell.CROSS, Cell.NONE, Cell.NONE
            ),
            listOf(
                Cell.CROSS, Cell.ZERO, Cell.NONE
            ),
            listOf(
                Cell.CROSS, Cell.ZERO, Cell.NONE
            )
        )
        val expected: Grid = mockk()
        val status = Status.Winner(player = Player.CROSS)
        every { gridRepository.getGrid() } returns grid
        every { gridRepository.updateGrid(cells = updatedCells, player = Player.ZERO, status = status) } returns expected

        val actual = interactor.cross(row = 2, col = 0)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN has horizontal winner WHEN cross THEN get correct grid`() {
        val grid = Grid(
            cells = listOf(
                listOf(
                    Cell.CROSS, Cell.CROSS, Cell.NONE
                ),
                listOf(
                    Cell.ZERO, Cell.ZERO, Cell.NONE
                ),
                listOf(
                    Cell.NONE, Cell.ZERO, Cell.NONE
                )
            ),
            currentPlayer = Player.CROSS,
            status = Status.Active,
        )
        val updatedCells = listOf(
            listOf(
                Cell.CROSS, Cell.CROSS, Cell.CROSS
            ),
            listOf(
                Cell.ZERO, Cell.ZERO, Cell.NONE
            ),
            listOf(
                Cell.NONE, Cell.ZERO, Cell.NONE
            )
        )
        val expected: Grid = mockk()
        val status = Status.Winner(player = Player.CROSS)
        every { gridRepository.getGrid() } returns grid
        every { gridRepository.updateGrid(cells = updatedCells, player = Player.ZERO, status = status) } returns expected

        val actual = interactor.cross(row = 0, col = 2)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN no winner WHEN zero THEN get correct grid`() {
        val grid = Grid(
            cells = listOf(
                listOf(
                    Cell.CROSS, Cell.NONE, Cell.ZERO
                ),
                listOf(
                    Cell.NONE, Cell.NONE, Cell.NONE
                ),
                listOf(
                    Cell.NONE, Cell.NONE, Cell.NONE
                )
            ),
            currentPlayer = Player.ZERO,
            status = Status.Active,
        )
        val updatedCells = listOf(
            listOf(
                Cell.CROSS, Cell.NONE, Cell.ZERO
            ),
            listOf(
                Cell.NONE, Cell.ZERO, Cell.NONE
            ),
            listOf(
                Cell.NONE, Cell.NONE, Cell.NONE
            )
        )
        val expected: Grid = mockk()
        every { gridRepository.getGrid() } returns grid
        every { gridRepository.updateGrid(cells = updatedCells, player = Player.CROSS) } returns expected

        val actual = interactor.zero(row = 1, col = 1)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN has diagonal winner WHEN zero THEN get correct grid`() {
        val grid = Grid(
            cells = listOf(
                listOf(
                    Cell.CROSS, Cell.NONE, Cell.ZERO
                ),
                listOf(
                    Cell.NONE, Cell.ZERO, Cell.NONE
                ),
                listOf(
                    Cell.NONE, Cell.NONE, Cell.NONE
                )
            ),
            currentPlayer = Player.CROSS,
            status = Status.Active,
        )
        val updatedCells = listOf(
            listOf(
                Cell.CROSS, Cell.NONE, Cell.ZERO
            ),
            listOf(
                Cell.NONE, Cell.ZERO, Cell.NONE
            ),
            listOf(
                Cell.ZERO, Cell.NONE, Cell.NONE
            )
        )
        val expected: Grid = mockk()
        val status = Status.Winner(player = Player.ZERO)
        every { gridRepository.getGrid() } returns grid
        every { gridRepository.updateGrid(cells = updatedCells, player = Player.CROSS, status = status) } returns expected

        val actual = interactor.zero(row = 2, col = 0)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN has vertical winner WHEN zero THEN get correct grid`() {
        val grid = Grid(
            cells = listOf(
                listOf(
                    Cell.CROSS, Cell.ZERO, Cell.ZERO
                ),
                listOf(
                    Cell.NONE, Cell.ZERO, Cell.NONE
                ),
                listOf(
                    Cell.NONE, Cell.NONE, Cell.NONE
                )
            ),
            currentPlayer = Player.CROSS,
            status = Status.Active,
        )
        val updatedCells = listOf(
            listOf(
                Cell.CROSS, Cell.ZERO, Cell.ZERO
            ),
            listOf(
                Cell.NONE, Cell.ZERO, Cell.NONE
            ),
            listOf(
                Cell.NONE, Cell.ZERO, Cell.NONE
            )
        )
        val expected: Grid = mockk()
        val status = Status.Winner(player = Player.ZERO)
        every { gridRepository.getGrid() } returns grid
        every { gridRepository.updateGrid(cells = updatedCells, player = Player.CROSS, status = status) } returns expected

        val actual = interactor.zero(row = 2, col = 1)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN has horizontal winner WHEN zero THEN get correct grid`() {
        val grid = Grid(
            cells = listOf(
                listOf(
                    Cell.CROSS, Cell.NONE, Cell.NONE
                ),
                listOf(
                    Cell.ZERO, Cell.ZERO, Cell.NONE
                ),
                listOf(
                    Cell.NONE, Cell.NONE, Cell.NONE
                )
            ),
            currentPlayer = Player.CROSS,
            status = Status.Active,
        )
        val updatedCells = listOf(
            listOf(
                Cell.CROSS, Cell.NONE, Cell.NONE
            ),
            listOf(
                Cell.ZERO, Cell.ZERO, Cell.ZERO
            ),
            listOf(
                Cell.NONE, Cell.NONE, Cell.NONE
            )
        )
        val expected: Grid = mockk()
        val status = Status.Winner(player = Player.ZERO)
        every { gridRepository.getGrid() } returns grid
        every { gridRepository.updateGrid(cells = updatedCells, player = Player.CROSS, status = status) } returns expected

        val actual = interactor.zero(row = 1, col = 2)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `WHEN reset THEN reset grid`() {
        val expected: Grid = mockk()
        every {
            gridRepository.updateGrid(
                cells = listOf(
                    listOf(Cell.NONE, Cell.NONE, Cell.NONE),
                    listOf(Cell.NONE, Cell.NONE, Cell.NONE),
                    listOf(Cell.NONE, Cell.NONE, Cell.NONE)
                ),
                player = Player.CROSS
            )
        } returns expected

        val actual = interactor.reset()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN draw situation WHEN cross THEN get draw status`() {
        val grid = Grid(
            cells = listOf(
                listOf(Cell.CROSS, Cell.ZERO, Cell.CROSS),
                listOf(Cell.CROSS, Cell.ZERO, Cell.ZERO),
                listOf(Cell.ZERO, Cell.CROSS, Cell.NONE)
            ),
            currentPlayer = Player.CROSS,
            status = Status.Active,
        )
        val updatedCells = listOf(
            listOf(Cell.CROSS, Cell.ZERO, Cell.CROSS),
            listOf(Cell.CROSS, Cell.ZERO, Cell.ZERO),
            listOf(Cell.ZERO, Cell.CROSS, Cell.CROSS)
        )
        val expected: Grid = mockk()
        val status = Status.Draw
        every { gridRepository.getGrid() } returns grid
        every { gridRepository.updateGrid(cells = updatedCells, player = Player.ZERO, status = status) } returns expected

        val actual = interactor.cross(row = 2, col = 2)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN cell already occupied WHEN cross THEN grid remains unchanged`() {
        val grid = Grid(
            cells = listOf(
                listOf(
                    Cell.CROSS, Cell.NONE, Cell.NONE
                ),
                listOf(
                    Cell.NONE, Cell.ZERO, Cell.NONE
                ),
                listOf(
                    Cell.NONE, Cell.NONE, Cell.NONE
                )
            ),
            currentPlayer = Player.CROSS,
            status = Status.Active,
        )
        val expected: Grid = mockk()
        every { gridRepository.getGrid() } returns grid
        every {
            gridRepository.updateGrid(
                cells = grid.cells,
                player = Player.CROSS,
                status = Status.Active
            )
        } returns expected

        val actual = interactor.cross(row = 1, col = 1)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN cell already occupied WHEN zero THEN grid remains unchanged`() {
        val grid = Grid(
            cells = listOf(
                listOf(
                    Cell.CROSS, Cell.NONE, Cell.NONE
                ),
                listOf(
                    Cell.NONE, Cell.ZERO, Cell.NONE
                ),
                listOf(
                    Cell.NONE, Cell.NONE, Cell.NONE
                )
            ),
            currentPlayer = Player.ZERO,
            status = Status.Active,
        )
        val expected: Grid = mockk()
        every { gridRepository.getGrid() } returns grid
        every {
            gridRepository.updateGrid(
                cells = grid.cells,
                player = Player.ZERO,
                status = Status.Active
            )
        } returns expected

        val actual = interactor.zero(row = 0, col = 0)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN full grid with winner WHEN cross THEN get winner status`() {
        val grid = Grid(
            cells = listOf(
                listOf(Cell.CROSS, Cell.CROSS, Cell.CROSS),
                listOf(Cell.ZERO, Cell.ZERO, Cell.CROSS),
                listOf(Cell.CROSS, Cell.ZERO, Cell.ZERO)
            ),
            currentPlayer = Player.CROSS,
            status = Status.Winner(Player.CROSS),
        )
        val expected: Grid = mockk()
        every { gridRepository.getGrid() } returns grid
        every { gridRepository.updateGrid(cells = grid.cells, player = Player.CROSS, status = Status.Winner(Player.CROSS)) } returns expected

        val actual = interactor.cross(row = 2, col = 2)
        Assert.assertEquals(expected, actual)
    }
}