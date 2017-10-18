package de.intektor.kentai_http_common.chat

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
class ChatMessageImage : ChatMessage {

    lateinit var hash: String

    constructor(hash: String, senderUUID: UUID, text: String, timeSent: Long) : super(senderUUID, text, timeSent) {
        this.hash = hash
    }

    constructor()

    override fun processAdditionalInfo(array: ByteArray?) {
        val byteIn = ByteArrayInputStream(array)
        val dataIn = DataInputStream(byteIn)
        hash = dataIn.readUTF()
    }

    override fun getAdditionalInfo(): ByteArray? {
        val byteOut = ByteArrayOutputStream()
        val dataOut = DataOutputStream(byteOut)
        dataOut.writeUTF(hash)
        return byteOut.toByteArray()
    }

    override fun encryptMessage(aesKey: Key, initVector: ByteArray) {

    }

    override fun decryptMessage(aesKey: Key, initVector: ByteArray) {

    }

    override fun shouldBeStored(): Boolean = true

    override fun isSmallData(): Boolean = true

    override fun hasReference(): Boolean = true

}