package net.rossharper.kleanplayer.home.presenter

import net.rossharper.kleanplayer.home.usecases.HomeViewLoadOutput

class HomePresenter : HomeViewLoadOutput {
    lateinit var viewGateway : ViewGateway

    // TODO: the presenter has lost its usual responsibilities due to view state and view models being known about in the interactor. It is now just a gateway to the "viewGateway" - the Android view model
    override fun updateState(state: HomeViewState) {
        viewGateway.updateView(state)
    }
}