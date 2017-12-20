package net.rossharper.kleanserviceclient

import net.rossharper.kleanserviceclient.datamodel.KleanServiceHomeStream
import net.rossharper.kleanserviceclient.home.HomeInteractor
import net.rossharper.kleanserviceclient.home.HomeParser
import net.rossharper.kleanserviceclient.home.StubHomeFetcher

fun createKleanServiceClient(httpGateway: HttpGateway): KleanServiceClient {
    val homeFetcher = StubHomeFetcher() // HomeFetcher(httpGateway)
    val homeParser = HomeParser()
    val homeInteractor = HomeInteractor(homeFetcher, homeParser)
    return KleanServiceClientFacade(homeInteractor)
}

interface KleanServiceClient {
    fun fetchHome(): HomeFetchResult
}

sealed class HomeFetchResult{
    data class Success(val homeStream: KleanServiceHomeStream) : HomeFetchResult()
    data class Error(val errorMessage: String) : HomeFetchResult()
}