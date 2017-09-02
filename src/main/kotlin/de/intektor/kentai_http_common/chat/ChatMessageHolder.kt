package de.intektor.kentai_http_common.chat

import java.util.*

/**
 * @author Intektor
 */
class ChatMessageHolder(senderUUID: UUID, text: String, timeSent: Long, id: UUID, aesKey: String, initVector: String) : ChatMessage(senderUUID, text, timeSent) {

    private var additionalInfo: ByteArray? = null

    init {
        this.id = id
        this.aesKey = aesKey
        this.initVector = initVector
    }

    override fun processAdditionalInfo(array: ByteArray?) {
        additionalInfo = array
    }

    override fun getAdditionalInfo(): ByteArray? = additionalInfo
}
