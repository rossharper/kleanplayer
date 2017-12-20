package net.rossharper.kleanplayer.home.domain

sealed class HomeState {
    object Loading : HomeState()
    data class Loaded(val homeStream: HomeStream) : HomeState()
    object Error : HomeState()
}