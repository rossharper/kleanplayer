package net.rossharper.kleanplayer.home.usecases

import kotlinx.coroutines.experimental.delay
import net.rossharper.kleanplayer.home.HomeStreamGateway
import net.rossharper.kleanplayer.home.view.ViewGateway

interface HomeViewLoadInput {
    suspend fun execute()
}

interface HomeViewLoadOutput {
    fun updateState(state: ViewGateway.HomeViewState) // TODO: wrong for view state to be here, DomainState?
}

class HomeViewLoadInteractor(
        private val output: HomeViewLoadOutput,
        private val homeStreamGateway: HomeStreamGateway
) : HomeViewLoadInput {
    suspend override fun execute() {
        output.updateState(ViewGateway.HomeViewState.Loading())

        delay(1500)

        val result = homeStreamGateway.getHome()
        when (result) {
            is HomeStreamGateway.Result.Success -> {
                // Translate the view model
                // TODO: should this not have been a presenter responsibility?

                if(Math.random() > 0.25) {

                    output.updateState(ViewGateway.HomeViewState.Success(
                            ViewGateway.HomeStream(result.homeStream.sections.map {
                                ViewGateway.Section(it.title)
                            })))

                } else {
                    output.updateState(ViewGateway.HomeViewState.Error())
                }
            }
            is HomeStreamGateway.Result.Error -> {
                output.updateState(ViewGateway.HomeViewState.Error())
            }
        }
    }
}