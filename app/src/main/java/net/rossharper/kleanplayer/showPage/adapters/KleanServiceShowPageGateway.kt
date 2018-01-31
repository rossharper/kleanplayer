package net.rossharper.kleanplayer.showPage.adapters

import net.rossharper.kleanplayer.kleanservice.createKleanServiceClient
import net.rossharper.kleanplayer.showPage.ShowId
import net.rossharper.kleanplayer.showPage.ShowPageGateway
import net.rossharper.kleanplayer.showPage.domain.Show
import net.rossharper.kleanserviceclient.HomeFetchResult
import net.rossharper.kleanserviceclient.KleanServiceClient
import net.rossharper.kleanserviceclient.datamodel.KleanServiceShowEntity

fun createShowPageGateway(): ShowPageGateway {
    val kleanServiceClient = createKleanServiceClient()
    return KleanServiceShowPageGateway(kleanServiceClient)
}

class KleanServiceShowPageGateway(private val kleanServiceClient: KleanServiceClient) : ShowPageGateway {
    override fun getShow(showId: ShowId): ShowPageGateway.ShowPageResult {
        val result = kleanServiceClient.fetchHome()
        return when(result) {
            is HomeFetchResult.Success -> {
                val show = result.homeStream.view.sections.flatMap {
                    it.entities
                }.filter {
                    it is KleanServiceShowEntity && it.id == showId.id
                }.first() as? KleanServiceShowEntity

                if (show is KleanServiceShowEntity) {
                    ShowPageGateway.ShowPageResult.Success(show.toShow())
                } else {
                    ShowPageGateway.ShowPageResult.Error
                }
            }
            is HomeFetchResult.Error -> {
                ShowPageGateway.ShowPageResult.Error
            }
        }
    }

}

private fun KleanServiceShowEntity.toShow(): Show {
    return Show(id, title)
}
