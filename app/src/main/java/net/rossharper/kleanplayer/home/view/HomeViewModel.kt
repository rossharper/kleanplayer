package net.rossharper.kleanplayer.home.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import net.rossharper.kleanplayer.home.HomeRouter
import net.rossharper.kleanplayer.home.HomeViewEventListener
import net.rossharper.kleanplayer.home.adapters.createHomeStreamGateway
import net.rossharper.kleanplayer.home.controller.HomeController
import net.rossharper.kleanplayer.home.domain.Item
import net.rossharper.kleanplayer.home.domain.StateHolder
import net.rossharper.kleanplayer.home.presenter.HomePresenter
import net.rossharper.kleanplayer.home.usecases.HomeViewLoadInteractor
import net.rossharper.kleanplayer.home.usecases.SelectItemInteractor

fun createHomeViewModel(): HomeViewModel {
    val homeStreamGateway = createHomeStreamGateway()
    val homePresenter = HomePresenter()
    val stateHolder = StateHolder()
    val homeViewLoad = HomeViewLoadInteractor(stateHolder, homeStreamGateway, homePresenter)
    val selectItem = SelectItemInteractor(stateHolder, homePresenter)
    val homeController = HomeController(homeViewLoad, selectItem)
    val homeViewModel = HomeViewModel(homeController)
    homePresenter.viewGateway = homeViewModel
    homePresenter.router = homeViewModel
    return homeViewModel
}

// TODO: is there a way to send view events directly to controller rather than via view model
class HomeViewModel(controller: HomeViewEventListener)
    : ViewModel(), ViewGateway, HomeViewEventListener by controller, HomeRouter {

    val homeViewLiveData = MutableLiveData<ViewGateway.HomeViewState>()

    var router : HomeRouter? = null

    override fun updateView(homeViewState: ViewGateway.HomeViewState) {
        homeViewLiveData.postValue(homeViewState)
    }

    override fun launchShowPage(showItem: Item.ShowItem) {
        router?.launchShowPage(showItem)
    }
}

