package net.rossharper.kleanplayer.home.usecases

import kotlinx.coroutines.experimental.delay
import net.rossharper.kleanplayer.home.HomeStreamGateway
import net.rossharper.kleanplayer.home.domain.HomeState
import net.rossharper.kleanplayer.home.domain.StateHolder

interface HomeViewLoadInput {
    suspend fun execute()
}

interface HomeViewLoadOutput {
    fun updateState(state: HomeState) // TODO: wrong for view state to be here, DomainState?
}

class HomeViewLoadInteractor(
        private val stateHolder: StateHolder,
        private val homeStreamGateway: HomeStreamGateway,
        private val output: HomeViewLoadOutput
) : HomeViewLoadInput {
    suspend override fun execute() {
        output.updateState(HomeState.Loading)

        delay(1500)

        val result = homeStreamGateway.getHome()
        val newState = when (result) {
            is HomeStreamGateway.Result.Success -> HomeState.Loaded(result.homeStream)
            is HomeStreamGateway.Result.Error -> HomeState.Error
        }

        stateHolder.state = newState
        output.updateState(newState) // TODO: could observe the above instead?
    }
}
