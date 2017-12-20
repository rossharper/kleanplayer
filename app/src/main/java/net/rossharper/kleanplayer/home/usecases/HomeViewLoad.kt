package net.rossharper.kleanplayer.home.usecases

import kotlinx.coroutines.experimental.delay
import net.rossharper.kleanplayer.home.HomeStreamGateway
import net.rossharper.kleanplayer.home.domain.HomeState

interface HomeViewLoadInput {
    suspend fun execute()
}

interface HomeViewLoadOutput {
    fun updateState(state: HomeState) // TODO: wrong for view state to be here, DomainState?
}

class HomeViewLoadInteractor(
        private val output: HomeViewLoadOutput,
        private val homeStreamGateway: HomeStreamGateway
) : HomeViewLoadInput {
    suspend override fun execute() {
        output.updateState(HomeState.Loading)

        delay(1500)

        val result = homeStreamGateway.getHome()
        when (result) {
            is HomeStreamGateway.Result.Success -> output.updateState(HomeState.Loaded(result.homeStream))
            is HomeStreamGateway.Result.Error -> output.updateState(HomeState.Error)
        }
    }
}