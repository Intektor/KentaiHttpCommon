package de.intektor.mercury_common

import de.intektor.mercury_common.chat.ChatMessage
import de.intektor.mercury_common.chat.ChatSerializer
import de.intektor.mercury_common.chat.MessageCore
import de.intektor.mercury_common.chat.data.MessageText
import de.intektor.mercury_common.chat.data.MessageVideo
import de.intektor.mercury_common.util.generateAESKey
import de.intektor.mercury_common.util.generateInitVector
import org.junit.Test
import java.util.*

/**
 * @author Intektor
 */
class ChatSerializerTest {

    @Test
    fun serializeChatMessage() {
        val core = MessageCore(UUID.nameUUIDFromBytes("Sender name".toByteArray()), 24242, UUID.nameUUIDFromBytes("Message uuid".toByteArray()))
        val data = MessageText("hallo")

        val message = ChatMessage(core, data)

        val aes = generateAESKey()
        val iV = generateInitVector()

        val serialized = ChatSerializer.serializeChatMessage(core, data, aes, iV)

        val deserialized = ChatSerializer.deserializeChatMessage(serialized, aes, iV)

        assert(message == deserialized)
    }

    @Test
    fun serializeVideoMessage() {
        val core = MessageCore(UUID.nameUUIDFromBytes("Sender name".toByteArray()), 24242, UUID.nameUUIDFromBytes("Message uuid".toByteArray()))
        val data = MessageVideo(3, false, 200, 200, byteArrayOf(), "hallo", generateAESKey(), generateInitVector(), UUID.randomUUID(), "efwefafafw")

        val message = ChatMessage(core, data)

        val aes = generateAESKey()
        val iV = generateInitVector()

        val serialized = ChatSerializer.serializeChatMessage(core, data, aes, iV)

        val deserialized = ChatSerializer.deserializeChatMessage(serialized, aes, iV)

        assert(data.text == (deserialized.messageData as MessageVideo).text)
    }
}