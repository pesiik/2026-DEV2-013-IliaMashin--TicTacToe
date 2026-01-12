package mc.pesiik.tictactoe.domain

sealed interface Status {
    data object Active : Status
    data object Draw : Status
    data class Winner(val player: Player, val winnerLine: List<Pair<Int, Int>>) : Status
}