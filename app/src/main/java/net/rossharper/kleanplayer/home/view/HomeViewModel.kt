package net.rossharper.kleanplayer.home.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import net.rossharper.kleanplayer.home.HomeViewEventListener
import net.rossharper.kleanplayer.home.adapters.createHomeStreamGateway
import net.rossharper.kleanplayer.home.controller.HomeController
import net.rossharper.kleanplayer.home.domain.Item
import net.rossharper.kleanplayer.home.domain.StateHolder
import net.rossharper.kleanplayer.home.presenter.HomePresenter
import net.rossharper.kleanplayer.home.adapters.HomeRouter
import net.rossharper.kleanplayer.home.usecases.HomeViewLoadInteractor
import net.rossharper.kleanplayer.home.usecases.SelectItemInteractor
import net.rossharper.kleanplayer.home.usecases.SelectItemOutput

fun createHomeViewModel(context: Context): HomeViewModel {
    val homeStreamGateway = createHomeStreamGateway()
    val homePresenter = HomePresenter()
    val stateHolder = StateHolder()
    val homeViewLoad = HomeViewLoadInteractor(stateHolder, homeStreamGateway, homePresenter)
    val homeRouter = HomeRouter(context)
    val selectItem = SelectItemInteractor(stateHolder, object : SelectItemOutput {
        override fun launchShowPage(showItem: Item.ShowItem) {
            homeRouter.launchShowPage(showItem)
        }
    })
    val homeController = HomeController(homeViewLoad, selectItem)
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
