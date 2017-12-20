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
            is HomeState.Loaded -> ViewGateway.HomeViewState.Loaded(state.homeStream.toViewModels())
            is HomeState.Error -> ViewGateway.HomeViewState.Error
        }
        viewGateway.updateView(homeViewState)
    }
}

private fun HomeStream.toViewModels(): ViewGateway.HomeStream {
    return ViewGateway.HomeStream(this.sections.toViewModels())
}

private fun List<Section>.toViewModels(): List<ViewGateway.Section> {
    return this.map {
        domainSection ->
        ViewGateway.Section(domainSection.title, domainSection.items.map { it.toViewModel() })
    }
}

private fun Item.toViewModel(): ViewGateway.Item {
    return when (this) {
        is Item.ShowItem -> ViewGateway.Item.ShowItem(title)
    }
}
