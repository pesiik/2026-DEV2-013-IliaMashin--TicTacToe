package mc.pesiik.tictactoe.data

import mc.pesiik.tictactoe.domain.Cell
import mc.pesiik.tictactoe.domain.Grid
import mc.pesiik.tictactoe.domain.Player
import mc.pesiik.tictactoe.domain.Status
import mc.pesiik.tictactoe.repository.GridRepository
import javax.inject.Inject

class GridRepositoryImpl @Inject constructor() : GridRepository {

    // I could make long-term storage here, but let's keep it simple for the kata
    private var currentGrid = Grid(
        cells = buildList {
            repeat(3) {
                add(List(3) { Cell.NONE })
            }
        },
        currentPlayer = Player.CROSS,
        status = Status.Active,
    )

    override fun getGrid(): Grid {
        return currentGrid
    }

    override fun updateGrid(cells: List<List<Cell>>, player: Player, status: Status): Grid {
        currentGrid = currentGrid.copy(
            cells = cells,
            currentPlayer = player,
            status = status,
        )
        return currentGrid
    }
}