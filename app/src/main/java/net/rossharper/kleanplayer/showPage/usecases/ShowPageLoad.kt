package net.rossharper.kleanplayer.showPage.usecases

import net.rossharper.kleanplayer.showPage.ShowId
import net.rossharper.kleanplayer.showPage.ShowPageGateway
import net.rossharper.kleanplayer.showPage.domain.ShowPageState


interface ShowPageLoadInput {
    fun execute(showId: ShowId)
}

interface ShowPageLoadOutput {
    fun updateState(state: ShowPageState)
}

class ShowPageLoadInteractor(
        private val showPageGateway: ShowPageGateway,
        val output: ShowPageLoadOutput
) : ShowPageLoadInput {
    override fun execute(showId: ShowId) {
        val result = showPageGateway.getShow(showId)
        when (result) {
            is ShowPageGateway.ShowPageResult.Success -> output.updateState(ShowPageState.Loaded(result.show))
            is ShowPageGateway.ShowPageResult.Error -> output.updateState(ShowPageState.Error)
        }
    }
}
