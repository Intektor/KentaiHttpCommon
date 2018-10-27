package de.intektor.mercury_common

import de.intektor.mercury_common.chat.ChatMessage
import de.intektor.mercury_common.chat.MessageCore
import de.intektor.mercury_common.chat.MessageData
import de.intektor.mercury_common.chat.data.MessageText
import de.intektor.mercury_common.gson.genGson
import org.junit.Test
import java.util.*

/**
 * @author Intektor
 */
class GsonTest {
    @Test
    fun saveMessageData() {
        val text = MessageText("test 123")

        val toJson = genGson().toJson(text, MessageData::class.java)

        val message = genGson().fromJson(toJson, MessageData::class.java)

        assert(text == message)
    }

    @Test
    fun saveChatMessage() {
        val core = MessageCore(UUID.randomUUID(), System.currentTimeMillis(), UUID.randomUUID())
        val data = MessageText("test 123")

        val original = ChatMessage(core, data)

        val toJson = genGson().toJson(original, ChatMessage::class.java)

        val serialized = genGson().fromJson(toJson, ChatMessage::class.java)

        assert(original == serialized)
    }
}