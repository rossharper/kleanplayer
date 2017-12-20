package net.rossharper.kleanplayer.home.view

interface ViewGateway {
    fun updateView(homeViewState: HomeViewState)

    sealed class HomeViewState {
        class Loading : HomeViewState()
        data class Success(val homeStreamViewModel: HomeStream) : HomeViewState()
        class Error : HomeViewState()
    }

    data class HomeStream(val sections: List<Section>)
    data class Section(val title: String)
}