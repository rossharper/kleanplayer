package net.rossharper.kleanplayer.showPage.view

import android.arch.lifecycle.ViewModel
import net.rossharper.kleanplayer.showPage.adapters.createShowPageGateway
import net.rossharper.kleanplayer.showPage.controller.ShowPageController
import net.rossharper.kleanplayer.showPage.presenter.ShowPagePresenter
import net.rossharper.kleanplayer.showPage.usecases.ShowPageLoadInteractor

fun createShowPageViewModel(): ShowPageViewModel {
    val presenter = ShowPagePresenter()
    val gateway = createShowPageGateway()
    val showPageLoad = ShowPageLoadInteractor(gateway, presenter)
    val controller = ShowPageController(showPageLoad)
    return ShowPageViewModel(controller)
}

class ShowPageViewModel(val controller: ShowPageController) : ViewModel()