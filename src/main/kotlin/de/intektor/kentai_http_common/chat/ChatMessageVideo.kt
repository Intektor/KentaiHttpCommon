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
class ChatMessageVideo : ChatMessage {

    lateinit var hash: String
    lateinit var durationSeconds: String
    lateinit var isGif: String

    constructor(hash: String, durationSeconds: Int, senderUUID: UUID, text: String, timeSent: Long, isGif: Boolean) : super(senderUUID, text, timeSent) {
        this.hash = hash
        this.durationSeconds = durationSeconds.toString()
        this.isGif = isGif.toString()
    }

    constructor()

    override fun processAdditionalInfo(array: ByteArray?) {
        val byteIn = ByteArrayInputStream(array)
        val dataIn = DataInputStream(byteIn)
        hash = dataIn.readUTF()
        durationSeconds = dataIn.readUTF()
        isGif = dataIn.readUTF()
    }

    override fun getAdditionalInfo(): ByteArray? {
        val byteOut = ByteArrayOutputStream()
        val dataOut = DataOutputStream(byteOut)
        dataOut.writeUTF(hash)
        dataOut.writeUTF(durationSeconds)
        dataOut.writeUTF(isGif)
        return byteOut.toByteArray()
    }

    override fun encryptMessage(aesKey: Key, initVector: ByteArray) {
        durationSeconds = durationSeconds.encryptAES(aesKey, initVector)
        isGif = isGif.encryptAES(aesKey, initVector)
    }

    override fun decryptMessage(aesKey: Key, initVector: ByteArray) {
        durationSeconds = durationSeconds.decryptAES(aesKey, initVector)
        isGif = isGif.decryptAES(aesKey, initVector)
    }

    override fun shouldBeStored(): Boolean = true

    override fun isSmallData(): Boolean = true

    override fun hasReference(): Boolean = true
}