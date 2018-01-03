package net.rossharper.kleanplayer.home.presenter

import net.rossharper.kleanplayer.home.domain.HomeState
import net.rossharper.kleanplayer.home.domain.HomeStream
import net.rossharper.kleanplayer.home.domain.Item
import net.rossharper.kleanplayer.home.domain.Section
import net.rossharper.kleanplayer.home.usecases.HomeViewLoadOutput
import net.rossharper.kleanplayer.home.view.ViewGateway

class HomePresenter : HomeViewLoadOutput {
    lateinit var viewGateway : ViewGateway

    override fun updateState(state: HomeState) {
        val homeViewState = when (state) {
            is HomeState.Loading -> ViewGateway.HomeViewState.Loading
            is HomeState.Loaded -> ViewGateway.HomeViewState.Loaded(state.homeStream.transformToViewModels())
            is HomeState.Error -> ViewGateway.HomeViewState.Error
        }
        viewGateway.updateView(homeViewState)
    }
}

private fun HomeStream.transformToViewModels(): ViewGateway.HomeStream {
    return ViewGateway.HomeStream(this.sections.transformToViewModels())
}

private fun List<Section>.transformToViewModels(): List<ViewGateway.Section> {
    return this.map {
        domainSection ->
        ViewGateway.Section(domainSection.title, domainSection.items.map { it.transformToViewModel() })
    }
}

private fun Item.transformToViewModel(): ViewGateway.Item {
    return when (this) {
        is Item.ShowItem -> ViewGateway.Item.ShowItem(title)
    }
}
