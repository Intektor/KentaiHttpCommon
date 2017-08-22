package de.intektor.kentai_http_common.chat

import java.util.*

/**
 * @author Intektor
 */
class ChatMessageText : ChatMessage {

    constructor(message: String, senderUUID: UUID, timeSent: Long) : super(senderUUID, message, timeSent)

    constructor() : super()

    override fun processAdditionalInfo(array: ByteArray?) {
    }

    override fun getAdditionalInfo(): ByteArray? = null
}
