package mc.pesiik.tictactoe.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import mc.pesiik.tictactoe.view.TicTacToeState

@Composable
fun TicTacToeGridBox(modifier: Modifier = Modifier, state: TicTacToeState, onCellTap: (Int, Int) -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawGridBorder(times = state.rows.size)
            }
    ) {
        TicTacToeGrid(state = state, onCellTap = onCellTap)
    }
}

@Composable
private fun TicTacToeGrid(state: TicTacToeState, onCellTap: (row: Int, col: Int) -> Unit) {
    val times = state.rows.size
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(times) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                repeat(times) { col ->
                    TicTacToeCell(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .clickable(
                                interactionSource = null,
                                indication = null,
                            ) {
                                onCellTap(row, col)
                            },
                        cellView = state.rows[row][col],
                    )
                }
            }
        }
    }
}

private fun DrawScope.drawGridBorder(times: Int) {
    repeat(times) { index ->
        if (index != 0) {
            val width = size.width / times * index
            val height = size.height / times * index
            val brush = Brush.linearGradient(
                colors = listOf(Color.Green, Color.Blue),
            )
            drawLine(
                brush = brush,
                start = Offset(
                    x = width,
                    y = 0f
                ),
                end = Offset(
                    x = width,
                    y = size.height
                )
            )
            drawLine(
                brush = brush,
                start = Offset(
                    x = 0f,
                    y = height
                ),
                end = Offset(
                    x = size.width,
                    y = height
                )
            )
        }
    }
}