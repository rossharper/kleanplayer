package net.rossharper.kleanplayer.home.adapters

import net.rossharper.kleanplayer.home.HomeStreamGateway
import net.rossharper.kleanplayer.home.domain.HomeStream
import net.rossharper.kleanplayer.home.domain.Item
import net.rossharper.kleanplayer.home.domain.Section
import net.rossharper.kleanplayer.kleanservice.createKleanServiceClient
import net.rossharper.kleanserviceclient.HomeFetchResult
import net.rossharper.kleanserviceclient.KleanServiceClient
import net.rossharper.kleanserviceclient.datamodel.KleanServiceEntity
import net.rossharper.kleanserviceclient.datamodel.KleanServiceHomeStream
import net.rossharper.kleanserviceclient.datamodel.KleanServiceSection
import net.rossharper.kleanserviceclient.datamodel.KleanServiceShowEntity

fun createHomeStreamGateway(): HomeStreamGateway {
    val kleanServiceClient = createKleanServiceClient()
    return KleanPlayerServiceHomeStreamGateway(kleanServiceClient)
}

private class KleanPlayerServiceHomeStreamGateway(private val kleanServiceClient: KleanServiceClient) : HomeStreamGateway {
    override fun getHome(): HomeStreamGateway.Result {
        val result = kleanServiceClient.fetchHome()
        return when(result) {
            is HomeFetchResult.Success -> {
                HomeStreamGateway.Result.Success(result.homeStream.transformToDomainModel())
            }
            is HomeFetchResult.Error -> HomeStreamGateway.Result.Error(result.errorMessage)
        }
    }
}

private fun KleanServiceHomeStream.transformToDomainModel() : HomeStream {
    return HomeStream(this.view.sections.map{ it.transformToDomainModel() })
}

private fun KleanServiceSection.transformToDomainModel(): Section {
    return Section(id, title, entities.mapNotNull { it.transformToDomainModel() })
}

private fun KleanServiceEntity.transformToDomainModel() : Item? {
    return when(this) {
        is KleanServiceShowEntity -> Item.ShowItem(id, title)
        else -> null
    }
}
