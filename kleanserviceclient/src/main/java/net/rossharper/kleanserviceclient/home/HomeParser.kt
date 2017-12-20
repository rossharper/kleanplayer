package net.rossharper.kleanserviceclient.home

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import net.rossharper.kleanserviceclient.JsonParser
import net.rossharper.kleanserviceclient.datamodel.KleanServiceEntity
import net.rossharper.kleanserviceclient.datamodel.KleanServiceHomeStream
import net.rossharper.kleanserviceclient.datamodel.KleanServiceShowEntity
import java.lang.reflect.Type

internal class HomeParser : JsonParser<KleanServiceHomeStream> {
    override fun parse(json: String): KleanServiceHomeStream =
            GsonBuilder()
                    .registerTypeHierarchyAdapter(KleanServiceEntity::class.java, EntityDeserializer())
                    .create()
                    .fromJson(json, KleanServiceHomeStream::class.java)
}

private class EntityDeserializer : JsonDeserializer<KleanServiceEntity?> {

    val gson = GsonBuilder().create()

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): KleanServiceEntity? {
        val jsonObject = json.asJsonObject
        return when (jsonObject["__typename"].asString) {
            "ShowEntity" -> gson.fromJson(jsonObject, KleanServiceShowEntity::class.java)
            else -> null
        }
    }
}
