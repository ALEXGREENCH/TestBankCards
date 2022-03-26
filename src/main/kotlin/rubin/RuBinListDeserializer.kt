package rubin

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type


class RuBinListDeserializer : JsonDeserializer<RuBinList> {

    @Throws(JsonParseException::class)
    override fun deserialize(element: JsonElement, type: Type?, context: JsonDeserializationContext): RuBinList {
        val jsonObject = element.asJsonObject
        val list: MutableList<RuBin> = ArrayList()
        for ((key, value) in jsonObject.entrySet()) {
            val ruBin = RuBin(key, value.asString)
            list.add(ruBin)
        }
        return RuBinList(list)
    }

}