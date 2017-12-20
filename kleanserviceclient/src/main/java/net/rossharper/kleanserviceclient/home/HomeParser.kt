package net.rossharper.kleanserviceclient.home

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import net.rossharper.kleanserviceclient.JsonParser
import net.rossharper.kleanserviceclient.datamodel.Entity
import net.rossharper.kleanserviceclient.datamodel.KleanServiceHomeStream
import net.rossharper.kleanserviceclient.datamodel.ShowEntity
import java.lang.reflect.Type

class HomeParser : JsonParser<KleanServiceHomeStream> {
    override fun parse(json: String): KleanServiceHomeStream =
            GsonBuilder()
                    .registerTypeHierarchyAdapter(Entity::class.java, EntityDeserializer())
                    .create()
                    .fromJson(json, KleanServiceHomeStream::class.java)
}

class EntityDeserializer : JsonDeserializer<Entity?> {

    val gson = GsonBuilder().create()

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Entity? {
        val jsonObject = json.asJsonObject
        return when (jsonObject["__typename"].asString) {
            "ShowEntity" -> gson.fromJson(jsonObject, ShowEntity::class.java)
            else -> null
        }
    }
}
