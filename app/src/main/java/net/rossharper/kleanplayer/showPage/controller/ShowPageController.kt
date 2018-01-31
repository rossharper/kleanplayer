package net.rossharper.kleanplayer.showPage.controller

import kotlinx.coroutines.experimental.launch
import net.rossharper.kleanplayer.showPage.ShowId
import net.rossharper.kleanplayer.showPage.usecases.ShowPageLoadInput

class ShowPageController(val showPageLoad: ShowPageLoadInput) {
    fun onShowPageLoad(showId: ShowId) {
        launch {
            showPageLoad.execute(showId)
        }
    }
}