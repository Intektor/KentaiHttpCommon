package de.intektor.kentai_http_common.chat

import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
class ChatMessageHolder(senderUUID: UUID, text: String, timeSent: Long, id: UUID, aesKey: String, initVector: String, signature: String, referenceUUID: UUID, hasReference: Boolean) :
        ChatMessage(senderUUID, text, timeSent) {

    private var additionalInfo: ByteArray? = null
    private var hasReference: Boolean

    init {
        this.id = id
        this.aesKey = aesKey
        this.initVector = initVector
        this.signature = signature
        this.referenceUUID = referenceUUID
        this.hasReference = hasReference
    }

    override fun processAdditionalInfo(array: ByteArray?) {
        additionalInfo = array
    }

    override fun getAdditionalInfo(): ByteArray? = additionalInfo

    override fun shouldBeStored(): Boolean = false

    override fun isSmallData(): Boolean = true

    override fun encryptMessage(aesKey: Key, initVector: ByteArray) {
    }

    override fun decryptMessage(aesKey: Key, initVector: ByteArray) {
    }

    override fun hasReference(): Boolean = hasReference
}
