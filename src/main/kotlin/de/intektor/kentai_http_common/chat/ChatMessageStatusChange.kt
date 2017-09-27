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
class ChatMessageStatusChange : ChatMessage {

    //All strings for encryption
    lateinit var chatUUID: String
    lateinit var messageUUID: String
    lateinit var status: String
    lateinit var time: String

    constructor(chatUUID: UUID, messageUUID: UUID, status: MessageStatus, time: Long, senderUUID: UUID, timeSent: Long) : super(senderUUID, "", timeSent) {
        this.chatUUID = chatUUID.toString()
        this.messageUUID = messageUUID.toString()
        this.status = status.ordinal.toString()
        this.time = time.toString()
    }

    constructor()

    override fun processAdditionalInfo(array: ByteArray?) {
        val byteIn = ByteArrayInputStream(array)
        val dataIn = DataInputStream(byteIn)
        chatUUID = dataIn.readUTF()
        messageUUID = dataIn.readUTF()
        status = dataIn.readUTF()
        time = dataIn.readUTF()
    }

    override fun getAdditionalInfo(): ByteArray? {
        val byteOut = ByteArrayOutputStream()
        val dataOut = DataOutputStream(byteOut)
        dataOut.writeUTF(chatUUID)
        dataOut.writeUTF(messageUUID)
        dataOut.writeUTF(status)
        dataOut.writeUTF(time)
        return byteOut.toByteArray()
    }

    override fun encryptMessage(aesKey: Key, initVector: ByteArray) {
        chatUUID = chatUUID.encryptAES(aesKey, initVector)
        messageUUID = messageUUID.encryptAES(aesKey, initVector)
        status = status.encryptAES(aesKey, initVector)
        time = time.encryptAES(aesKey, initVector)
    }

    override fun decryptMessage(aesKey: Key, initVector: ByteArray) {
        chatUUID = chatUUID.decryptAES(aesKey, initVector)
        messageUUID = messageUUID.decryptAES(aesKey, initVector)
        status = status.decryptAES(aesKey, initVector)
        time = time.decryptAES(aesKey, initVector)
    }

    override fun shouldBeStored(): Boolean = false

    override fun isSmallData(): Boolean = true

    override fun hasReference(): Boolean = false
}