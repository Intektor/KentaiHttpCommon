package de.intektor.kentai_http_common.client_to_server

import de.intektor.kentai_http_common.chat.ChatMessage
import de.intektor.kentai_http_common.util.encryptRSA
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*

/**
 * @author Intektor
 */
class SendChatMessageRequest(val list: List<SendingMessage>, val senderUUID: UUID, val senderUUIDEncrypted: String) {
    companion object {
        val TARGET: String = "sendChatMessage"
    }

    data class SendingMessage(val message: ChatMessage, val receiverUUID: UUID, val chatUUID: String, var messageRegistryId: String) {
        fun encrypt(senderPrivate: RSAPrivateKey, receiverPublic: RSAPublicKey) {
            message.encrypt(senderPrivate, receiverPublic)
            messageRegistryId = messageRegistryId.encryptRSA(receiverPublic)
        }
    }
}