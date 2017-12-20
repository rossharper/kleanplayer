package net.rossharper.kleanplayer.home.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import net.rossharper.kleanplayer.home.HomeViewEventListener
import net.rossharper.kleanplayer.home.adapters.createHomeStreamGateway
import net.rossharper.kleanplayer.home.controller.HomeController
import net.rossharper.kleanplayer.home.presenter.HomePresenter
import net.rossharper.kleanplayer.home.usecases.HomeViewLoadInteractor

fun createHomeViewModel(): HomeViewModel {
    val homeStreamGateway = createHomeStreamGateway()
    val homePresenter = HomePresenter()
    val homeViewLoad = HomeViewLoadInteractor(homePresenter, homeStreamGateway)
    val homeController = HomeController(homeViewLoad)
    val homeViewModel = HomeViewModel(homeController)
    homePresenter.viewGateway = homeViewModel
    return homeViewModel
}

// TODO: is there a way to send view events directly to controller rather than via view model
class HomeViewModel(controller: HomeViewEventListener) : ViewModel(), ViewGateway, HomeViewEventListener by controller {
    val homeViewLiveData = MutableLiveData<ViewGateway.HomeViewState>()

    override fun updateView(homeViewState: ViewGateway.HomeViewState) {
        homeViewLiveData.postValue(homeViewState)
    }
}
