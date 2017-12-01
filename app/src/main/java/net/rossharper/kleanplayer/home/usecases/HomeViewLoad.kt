package net.rossharper.kleanplayer.home.usecases

import kotlinx.coroutines.experimental.delay
import net.rossharper.kleanplayer.home.HomeStreamGateway
import net.rossharper.kleanplayer.home.presenter.HomeViewState
import net.rossharper.kleanplayer.home.view.HomeStreamViewModel
import net.rossharper.kleanplayer.home.view.SectionViewModel

interface HomeViewLoadInput {
    suspend fun execute()
}

interface HomeViewLoadOutput {
    fun updateState(state: HomeViewState)
}

class HomeViewLoadInteractor(
        private val output: HomeViewLoadOutput,
        private val homeStreamGateway: HomeStreamGateway
) : HomeViewLoadInput {
    suspend override fun execute() {
        output.updateState(HomeViewState.Loading())

        delay(1500)

        val result = homeStreamGateway.getHome()
        when (result) {
            is HomeStreamGateway.Result.Success -> {
                // Translate the view model
                // TODO: should this not have been a presenter responsibility?

                if(Math.random() > 0.25) {

                    output.updateState(HomeViewState.Success(
                            HomeStreamViewModel(result.homeStream.sections.map {
                                SectionViewModel(it.title)
                            })))

                } else {
                    output.updateState(HomeViewState.Error())
                }
            }
            is HomeStreamGateway.Result.Error -> {
                output.updateState(HomeViewState.Error())
            }
        }
    }
}