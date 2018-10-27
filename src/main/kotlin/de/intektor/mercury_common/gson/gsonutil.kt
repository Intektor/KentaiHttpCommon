package de.intektor.mercury_common.gson

import com.google.common.io.BaseEncoding
import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import de.intektor.mercury_common.chat.MessageData
import de.intektor.mercury_common.chat.MessageDataRegistry
import de.intektor.mercury_common.chat.data.group_modification.GroupModification
import de.intektor.mercury_common.chat.data.group_modification.GroupModificationRegistry
import de.intektor.mercury_common.util.toAESKey
import de.intektor.mercury_common.util.toKey
import java.lang.reflect.Type
import java.security.Key
import java.security.interfaces.RSAPublicKey

/**
 * @author Intektor
 */

private val gson = GsonBuilder().apply {
    registerTypeAdapter(RSAPublicKey::class.java, RSAPublicKeyTypeAdapter())
    registerTypeAdapter(MessageData::class.java, MessageDataSerializer())
    registerTypeAdapter(Key::class.java, KeyTypeAdapter())
}.create()

fun genGson(): Gson = gson

private class RSAPublicKeyTypeAdapter : TypeAdapter<RSAPublicKey>() {
    override fun write(output: JsonWriter, value: RSAPublicKey) {
        output.beginObject()
        output.name("encoded").value(BaseEncoding.base64().encode(value.encoded))
        output.endObject()
    }

    override fun read(input: JsonReader): RSAPublicKey {
        input.beginObject()
        if (input.nextName() == "encoded") {
            val key = input.nextString().toKey() as RSAPublicKey
            input.endObject()
            return key
        }
        throw RuntimeException()
    }
}

private class KeyTypeAdapter : TypeAdapter<Key>() {

    companion object {
        private const val KEY = "key"
    }

    override fun write(out: JsonWriter, value: Key) {
        out.beginObject()
        out.name(KEY).value(BaseEncoding.base64().encode(value.encoded))
        out.endObject()
    }

    override fun read(input: JsonReader): Key {
        input.beginObject()
        input.nextName()
        val key = input.nextString().toAESKey()
        input.endObject()

        return key
    }

}

private class GroupModificationAdapter : TypeAdapter<GroupModification>() {

    private companion object {
        private const val NAME_MODIFICATION_TYPE = "modification_type"
        private const val NAME_MODIFICATION_DATA = "modification_data"
    }

    override fun write(output: JsonWriter, value: GroupModification) {
        output.beginObject()
        output.name(NAME_MODIFICATION_TYPE).value(GroupModificationRegistry.getID(value.javaClass)
                ?: throw IllegalStateException("No modification type found for $value"))

        output.name(NAME_MODIFICATION_DATA).value(Gson().toJson(value))
        output.endObject()
    }

    override fun read(input: JsonReader): GroupModification {
        input.beginObject()
        input.nextName()
        val dataType = input.nextInt()
        input.nextName()
        val messageData = Gson().fromJson(input.nextString(), GroupModificationRegistry.getClass(dataType))
        input.endObject()
        return messageData
    }
}

private class MessageDataSerializer : JsonSerializer<MessageData>, JsonDeserializer<MessageData> {

    companion object {
        private const val NAME_TYPE = "type"
        private const val NAME_DATA = "data"
    }

    override fun serialize(src: MessageData, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val jsonObject = JsonObject()

        jsonObject.addProperty(NAME_TYPE, MessageDataRegistry.getID(src.javaClass))

        jsonObject.add(NAME_DATA, context.serialize(src))

        return jsonObject
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): MessageData {
        val jsonObject = json.asJsonObject

        val type = jsonObject.get(NAME_TYPE).asInt

        return context.deserialize(jsonObject.get(NAME_DATA), MessageDataRegistry.getClass(type))
    }

}