package de.intektor.mercury_common.client_to_server

import java.util.*

/**
 * @param list the list of messages that should be sent
 * @param senderUUID the userUUID of the sender
 * @param signature the signature of the userUUID as a String signed with the private auth key
 * @author Intektor
 */
class SendChatMessageRequest(val list: List<SendingMessage>, val senderUUID: UUID, val signature: ByteArray) {
    companion object {
        val TARGET: String = "sendChatMessage"
    }

    /**
     * @param signature the signature of message
     */
    @Suppress("ArrayInDataClass")
    data class SendingMessage(val message: String, val messageUUID: UUID, val receiverUUID: UUID, val chatUUID: String, val aesKey: String, val initVector: String, val signature: ByteArray)
}