package net.rossharper.kleanplayer.kleanservice

import net.rossharper.kleanserviceclient.KleanServiceClient
import net.rossharper.kleanserviceclient.createKleanServiceClient

fun createKleanServiceClient(): KleanServiceClient {
    val httpGateway = createHttpGateway()
    return createKleanServiceClient(httpGateway)
}