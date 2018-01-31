package net.rossharper.kleanplayer.showPage.domain

sealed class ShowPageState {
    class Loaded(show: Show) : ShowPageState()
    object Error : ShowPageState()
}