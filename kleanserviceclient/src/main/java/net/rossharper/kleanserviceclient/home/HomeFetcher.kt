package net.rossharper.kleanserviceclient.home

import net.rossharper.kleanserviceclient.HttpGateway
import net.rossharper.kleanserviceclient.JsonFetcher

internal class HomeFetcher(private val httpGateway: HttpGateway) : JsonFetcher {
    override fun fetch(): JsonFetcher.Result {
        val request = httpGateway.requestBuilder().url("https://raw.githubusercontent.com/rossharper/kleanplayer/master/kleanserviceclient/src/main/resources/data.json").build()
        val response = httpGateway.execute(request)
        return when(response) {
            is HttpGateway.Response.Success -> JsonFetcher.Result.Success(response.body)
            else -> JsonFetcher.Result.Error()
        }
    }

}