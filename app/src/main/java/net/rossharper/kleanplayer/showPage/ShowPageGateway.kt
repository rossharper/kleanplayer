package net.rossharper.kleanplayer.showPage

import net.rossharper.kleanplayer.showPage.domain.Show

interface ShowPageGateway {

    sealed class ShowPageResult {
        class Success(val show: Show) : ShowPageResult()
        object Error : ShowPageResult()
    }

    fun getShow(showId: ShowId) : ShowPageResult
}


