package net.rossharper.kleanplayer.home.domain

sealed class HomeState {
    object Loading : HomeState()
    data class Loaded(val homeStream: HomeStream) : HomeState()
    object Error : HomeState()
}

// TODO: how to have an immutable interface to this for non-modifying interactors?
class StateHolder(var state: HomeState = HomeState.Loading)