package mc.pesiik.tictactoe.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mc.pesiik.tictactoe.domain.Cell
import mc.pesiik.tictactoe.view.TicTacToeState

@Composable
fun TicTacToeCell(
    modifier: Modifier = Modifier,
    cellView: TicTacToeState.CellView,
) {
    val progress by animateFloatAsState(
        targetValue = if (cellView.cell != Cell.NONE) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "cellAnimation"
    )
    Box(
        modifier = modifier
            .padding(8.dp)
            .drawWithCache {
                when (cellView.cell) {
                    Cell.NONE -> clear()
                    Cell.CROSS -> drawCross(progress)
                    Cell.ZERO -> drawZero(progress)
                }
            }
    )
}

private fun CacheDrawScope.drawCross(progress: Float): DrawResult {
    return onDrawBehind {
        val strokeWidth = size.width * 0.1f
        val brush = Brush.linearGradient(
            colors = listOf(Color.Yellow, Color.Blue)
        )
        val progressEnd = Offset(
            x = (size.width - strokeWidth / 2) * progress,
            y = (size.height - strokeWidth / 2) * progress
        )
        drawLine(
            brush = brush,
            strokeWidth = strokeWidth,
            start = Offset(0f + strokeWidth / 2, 0f + strokeWidth / 2),
            end = Offset(
                x = strokeWidth / 2 + progressEnd.x,
                y = strokeWidth / 2 + progressEnd.y
            ),
        )
        val progressEnd2 = Offset(
            x = (size.width - strokeWidth) * progress,
            y = (size.height - strokeWidth) * progress
        )
        drawLine(
            brush = brush,
            strokeWidth = strokeWidth,
            start = Offset(size.width - strokeWidth / 2, 0f + strokeWidth / 2),
            end = Offset(
                x = size.width - strokeWidth / 2 - progressEnd2.x,
                y = strokeWidth / 2 + progressEnd2.y
            ),
        )
    }
}

private fun CacheDrawScope.drawZero(progress: Float): DrawResult {
    return onDrawBehind {
        val strokeWidth = size.width * 0.1f
        val brush = Brush.linearGradient(
            colors = listOf(Color.Cyan, Color.Magenta)
        )
        drawCircle(
            brush = brush,
            radius = ((size.minDimension - strokeWidth) / 2) * progress,
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
        )
    }
}

private fun CacheDrawScope.clear(): DrawResult {
    return onDrawBehind {
        // Do nothing, effectively clearing the cell
    }
}