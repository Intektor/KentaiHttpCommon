package de.intektor.kentai_http_common.client_to_server

import de.intektor.kentai_http_common.chat.ChatMessage
import de.intektor.kentai_http_common.util.encryptRSA
import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
class SendChatMessageRequest(val list: List<SendingMessage>) {
    companion object {
        val TARGET: String = "sendChatMessage"
    }

    data class SendingMessage(val message: ChatMessage, val senderUUID: UUID, val receiverUUIDEncrypted: String, val chatUUID: UUID, var messageRegistryId: String, val previewText: String) {
        fun encrypt(key: Key) {
            message.encrypt(key)
            messageRegistryId = messageRegistryId.encryptRSA(key)
        }
    }
}