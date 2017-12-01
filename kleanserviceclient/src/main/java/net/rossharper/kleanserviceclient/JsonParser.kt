package net.rossharper.kleanserviceclient


interface JsonParser<T> {

    class ParseException : Exception()

    fun parse(json: String): T
}