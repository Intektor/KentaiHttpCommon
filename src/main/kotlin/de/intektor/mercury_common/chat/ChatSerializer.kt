package de.intektor.mercury_common.chat

import de.intektor.mercury_common.gson.genGson
import de.intektor.mercury_common.util.decryptAES
import de.intektor.mercury_common.util.encryptAES
import java.security.Key

/**
 * @author Intektor
 */
object ChatSerializer {

    private val gson = genGson()

    fun serializeChatMessage(chatMessage: MessageCore, messageData: MessageData, aesKey: Key, initVector: ByteArray): String {
        val serializedMessage = gson.toJson(SerializedChatMessage(chatMessage, messageData))

        return serializedMessage.encryptAES(aesKey, initVector)
    }

    fun deserializeChatMessage(input: String, aesKey: Key, initVector: ByteArray): ChatMessage {
        val serializedMessage = input.decryptAES(aesKey, initVector)

        val message = gson.fromJson(serializedMessage, SerializedChatMessage::class.java)

        return ChatMessage(message.chatMessage, message.messageData)
    }

    private class SerializedChatMessage(val chatMessage: MessageCore, val messageData: MessageData)
}