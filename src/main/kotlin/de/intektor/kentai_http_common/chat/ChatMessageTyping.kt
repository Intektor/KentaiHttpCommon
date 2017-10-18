package de.intektor.kentai_http_common.chat

import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
class ChatMessageTyping : ChatMessage {

    constructor(senderUUID: UUID, text: String, timeSent: Long) : super(senderUUID, text, timeSent)

    constructor()

    override fun processAdditionalInfo(array: ByteArray?) {
    }

    override fun getAdditionalInfo(): ByteArray? = null

    override fun encryptMessage(aesKey: Key, initVector: ByteArray) {
    }

    override fun decryptMessage(aesKey: Key, initVector: ByteArray) {
    }

    override fun shouldBeStored(): Boolean = false

    override fun isSmallData(): Boolean = false

    override fun hasReference(): Boolean = false

}