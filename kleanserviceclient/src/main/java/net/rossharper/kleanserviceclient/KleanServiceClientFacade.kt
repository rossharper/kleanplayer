package net.rossharper.kleanserviceclient

import net.rossharper.kleanserviceclient.home.HomeInteractor

internal class KleanServiceClientFacade(private val homeInteractor: HomeInteractor) : KleanServiceClient {
    override fun fetchHome(): HomeFetchResult = homeInteractor.fetchHomeStream()

}