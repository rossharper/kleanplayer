package net.rossharper.kleanserviceclient


interface JsonFetcher {

    sealed class Result {
        data class Success(val json: String) : Result()
        class Error : Result()
    }

    fun fetch(): Result
}