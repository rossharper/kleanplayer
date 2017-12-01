package net.rossharper.kleanplayer.home

import net.rossharper.kleanplayer.home.domain.HomeStream

interface HomeStreamGateway {
    sealed class Result {
        data class Success(val homeStream: HomeStream): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun getHome(): Result
}
