package net.rossharper.kleanplayer.home.controller

import kotlinx.coroutines.experimental.launch
import net.rossharper.kleanplayer.home.HomeViewEventListener
import net.rossharper.kleanplayer.home.usecases.HomeViewLoadInput

class HomeController(private val homeViewLoad: HomeViewLoadInput) : HomeViewEventListener {
    override fun onHomeViewLoad() {
        launch { homeViewLoad.execute() }
    }

    override fun onRefresh() {
        launch { homeViewLoad.execute() }
    }

    override fun onRetry() {
        launch { homeViewLoad.execute() }
    }
}


