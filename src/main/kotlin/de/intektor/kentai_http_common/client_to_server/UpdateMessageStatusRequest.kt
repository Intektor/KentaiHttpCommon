package de.intektor.kentai_http_common.client_to_server

import de.intektor.kentai_http_common.chat.MessageStatus
import java.util.*

/**
 * @author Intektor
 */
class UpdateMessageStatusRequest(val encryptedMessageID: String, val messageStatus: MessageStatus, val time: Long, val senderUUID: UUID, val receiverUUID: UUID, val chatUUID: UUID) {
    companion object {
        val TARGET: String = "updateMessageStatus"
    }
}