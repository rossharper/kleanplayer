package net.rossharper.kleanserviceclient.home

import net.rossharper.kleanserviceclient.JsonFetcher

class StubHomeFetcher : JsonFetcher {
    override fun fetch(): JsonFetcher.Result {
        return JsonFetcher.Result.Success(this.javaClass.classLoader.getResource("data.json").readText())
    }
}
