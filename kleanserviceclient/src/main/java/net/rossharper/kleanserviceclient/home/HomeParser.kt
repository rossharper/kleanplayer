package net.rossharper.kleanserviceclient.home

import com.google.gson.GsonBuilder
import net.rossharper.kleanserviceclient.JsonParser
import net.rossharper.kleanserviceclient.datamodel.KleanServiceHomeStream

class HomeParser : JsonParser<KleanServiceHomeStream> {
    override fun parse(json: String): KleanServiceHomeStream =
            GsonBuilder().create().fromJson(json, KleanServiceHomeStream::class.java)
}