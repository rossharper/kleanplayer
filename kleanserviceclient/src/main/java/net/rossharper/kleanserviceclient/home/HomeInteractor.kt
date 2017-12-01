package net.rossharper.kleanserviceclient.home

import net.rossharper.kleanserviceclient.HomeFetchResult
import net.rossharper.kleanserviceclient.JsonFetcher
import net.rossharper.kleanserviceclient.JsonParser


class HomeInteractor(private val homeFetcher: HomeFetcher, private val homeParser: HomeParser) {
    fun fetchHomeStream(): HomeFetchResult {
        val fetchResult = homeFetcher.fetch()
        return when (fetchResult) {
            is JsonFetcher.Result.Success -> {
                try {
                    HomeFetchResult.Success(homeParser.parse(fetchResult.json))
                } catch (e: JsonParser.ParseException) {
                    HomeFetchResult.Error("Failed to parse")
                }
            }
            is JsonFetcher.Result.Error -> {
                HomeFetchResult.Error("Failed to fetch")
            }
        }
    }
}