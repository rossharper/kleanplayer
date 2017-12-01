package net.rossharper.kleanplayer.home.adapters

import net.rossharper.kleanplayer.home.HomeStreamGateway
import net.rossharper.kleanplayer.home.domain.HomeStream
import net.rossharper.kleanplayer.home.domain.Section
import net.rossharper.kleanserviceclient.HomeFetchResult
import net.rossharper.kleanserviceclient.KleanServiceClient
import net.rossharper.kleanserviceclient.createKleanServiceClient

fun createHomeStreamGateway(): HomeStreamGateway {
    val httpGateway = createHttpGateway()
    val kleanServiceClient = createKleanServiceClient(httpGateway)
    val homeStreamGateway = KleanPlayerServiceHomeStreamGateway(kleanServiceClient)
    return homeStreamGateway
}

private class KleanPlayerServiceHomeStreamGateway(private val kleanServiceClient: KleanServiceClient) : HomeStreamGateway {
    override fun getHome(): HomeStreamGateway.Result {
        val result = kleanServiceClient.fetchHome()
        return when(result) {
            is HomeFetchResult.Success -> {
                HomeStreamGateway.Result.Success(HomeStream(result.homeStream.view.sections.map {
                    Section(it.id, it.title)
                }))
            }
            is HomeFetchResult.Error -> HomeStreamGateway.Result.Error(result.errorMessage)
        }
    }
}