package net.rossharper.kleanserviceclient

interface HttpGateway {

    interface Request {
        val url: String
    }

    sealed class Response {
        data class Success(val body: String) : Response()
        data class HttpError(val httpCode: Int) : Response()
        class Error : Response()
    }

    interface RequestBuilder {
        fun url(url: String): RequestBuilder
        fun build(): Request
    }

    fun requestBuilder(): RequestBuilder

    fun execute(request: Request): Response
}