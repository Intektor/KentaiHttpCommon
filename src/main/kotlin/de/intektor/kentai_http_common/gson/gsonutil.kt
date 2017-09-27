package de.intektor.kentai_http_common.gson

import com.google.common.io.BaseEncoding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import de.intektor.kentai_http_common.chat.ChatMessage
import de.intektor.kentai_http_common.chat.ChatMessageHolder
import de.intektor.kentai_http_common.util.toKey
import de.intektor.kentai_http_common.util.toUUID
import java.security.interfaces.RSAPublicKey
import java.util.*

/**
 * @author Intektor
 */
fun genGson(): Gson {
    val builder = GsonBuilder()
    builder.registerTypeAdapter(RSAPublicKey::class.java, RSAPublicKeyTypeAdapter())
    builder.registerTypeAdapter(ChatMessage::class.java, ChatMessageTypeAdapter())
    return builder.create()
}

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

private class ChatMessageTypeAdapter : TypeAdapter<ChatMessage>() {

    override fun read(input: JsonReader): ChatMessage {
        input.beginObject()
        input.nextName()
        val text = input.nextString()
        input.nextName()
        val timeSent = input.nextLong()
        input.nextName()
        val id = UUID.fromString(input.nextString())
        input.nextName()
        val senderUUID = UUID.fromString(input.nextString())
        input.nextName()
        val aesKey = input.nextString()
        input.nextName()
        val initVector = input.nextString()
        input.nextName()
        val signature = input.nextString()
        input.nextName()
        val referenceUUID = input.nextString().toUUID()
        input.nextName()
        val hasReference = input.nextBoolean()
        val additionalInfo: ByteArray? = if (input.hasNext()) {
            input.nextName()
            BaseEncoding.base64().decode(input.nextString())
        } else {
            null
        }
        input.endObject()
        val holder = ChatMessageHolder(senderUUID, text, timeSent, id, aesKey, initVector, signature, referenceUUID, hasReference)
        holder.processAdditionalInfo(additionalInfo)
        return holder
    }

    override fun write(output: JsonWriter, value: ChatMessage) {
        output.beginObject()
        output.name("text").value(value.text)
        output.name("timeSent").value(value.timeSent)
        output.name("id").value(value.id.toString())
        output.name("senderUUID").value(value.senderUUID.toString())
        output.name("aesKey").value(value.aesKey)
        output.name("initVector").value(value.initVector)
        output.name("signature").value(value.signature)
        output.name("referenceUUID").value(value.referenceUUID.toString())
        output.name("hasReference").value(value.hasReference())
        val additionalInfo = value.getAdditionalInfo()
        if (additionalInfo != null) {
            output.name("additionalInfo").value(BaseEncoding.base64().encode(additionalInfo))
        }
        output.endObject()
    }

}