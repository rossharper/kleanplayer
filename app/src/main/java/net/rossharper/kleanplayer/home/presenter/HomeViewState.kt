package net.rossharper.kleanplayer.home.presenter

import net.rossharper.kleanplayer.home.view.HomeStreamViewModel

sealed class HomeViewState {
    class Loading : HomeViewState()
    data class Success(val homeStreamViewModel: HomeStreamViewModel) : HomeViewState()
    class Error : HomeViewState()
}