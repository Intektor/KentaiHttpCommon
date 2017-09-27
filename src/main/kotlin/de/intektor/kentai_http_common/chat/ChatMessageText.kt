package de.intektor.kentai_http_common.chat

import java.security.Key
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

    override fun shouldBeStored(): Boolean = true

    override fun isSmallData(): Boolean = true

    override fun encryptMessage(aesKey: Key, initVector: ByteArray) {
    }

    override fun decryptMessage(aesKey: Key, initVector: ByteArray) {
    }

    override fun hasReference(): Boolean = false

}
