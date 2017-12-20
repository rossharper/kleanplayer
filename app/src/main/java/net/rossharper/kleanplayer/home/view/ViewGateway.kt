package net.rossharper.kleanplayer.home.view

interface ViewGateway {
    fun updateView(homeViewState: HomeViewState)

    sealed class HomeViewState {
        object Loading : HomeViewState()
        data class Loaded(val homeStreamViewModel: HomeStream) : HomeViewState()
        object Error : HomeViewState()
    }

    data class HomeStream(val sections: List<Section>)
    data class Section(val title: String, val items: List<Item>)
    sealed class Item {
        data class ShowItem(val title: String) : Item()
    }
}