package net.rossharper.kleanplayer.kleanservice

import net.rossharper.kleanserviceclient.HttpGateway
import okhttp3.OkHttpClient
import okhttp3.Request

fun createHttpGateway(): HttpGateway {
    return object : HttpGateway {
        val httpClient = OkHttpClient.Builder().build()

        override fun requestBuilder(): HttpGateway.RequestBuilder {
            return object : HttpGateway.RequestBuilder {
                private var builderUrl: String? = null

                override fun url(url: String): HttpGateway.RequestBuilder =
                        this.apply { builderUrl = url }

                override fun build(): HttpGateway.Request {
                    return object : HttpGateway.Request {
                        override val url: String
                            get() = builderUrl!!
                    }
                }
            }
        }

        override fun execute(request: HttpGateway.Request): HttpGateway.Response {
            val httpRequest = Request.Builder().url(request.url).build()
            return try {
                val response = httpClient.newCall(httpRequest).execute()
                when (response.code()) {
                    200 -> HttpGateway.Response.Success(response.body()!!.string())
                    else -> HttpGateway.Response.HttpError(response.code())
                }
            } catch (_: Exception) {
                HttpGateway.Response.Error()
            }
        }
    }
}