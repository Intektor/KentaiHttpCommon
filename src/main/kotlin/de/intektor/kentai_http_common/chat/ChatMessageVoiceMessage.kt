package de.intektor.kentai_http_common.chat

import de.intektor.kentai_http_common.util.decryptAES
import de.intektor.kentai_http_common.util.encryptAES
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
class ChatMessageVoiceMessage : ChatMessage {

    lateinit var durationSeconds: String

    constructor(durationSeconds: Int, referenceUUID: UUID, senderUUID: UUID, text: String, timeSent: Long) : super(senderUUID, text, timeSent) {
        this.durationSeconds = durationSeconds.toString()
        this.referenceUUID = referenceUUID
    }

    constructor()

    override fun processAdditionalInfo(array: ByteArray?) {
        val byteIn = ByteArrayInputStream(array)
        val dataIn = DataInputStream(byteIn)
        durationSeconds = dataIn.readUTF()
    }

    override fun getAdditionalInfo(): ByteArray? {
        val byteOut = ByteArrayOutputStream()
        val dataOut = DataOutputStream(byteOut)
        dataOut.writeUTF(durationSeconds)
        return byteOut.toByteArray()
    }

    override fun encryptMessage(aesKey: Key, initVector: ByteArray) {
        durationSeconds = durationSeconds.encryptAES(aesKey, initVector)
    }

    override fun decryptMessage(aesKey: Key, initVector: ByteArray) {
        durationSeconds = durationSeconds.decryptAES(aesKey, initVector)
    }

    override fun shouldBeStored(): Boolean = true

    override fun isSmallData(): Boolean = true

    override fun hasReference(): Boolean = true
}