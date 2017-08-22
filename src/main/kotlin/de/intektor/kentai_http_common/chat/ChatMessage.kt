package de.intektor.kentai_http_common.chat

import de.intektor.kentai_http_common.util.*
import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
abstract class ChatMessage {

    lateinit var senderUUID: UUID
    /**
     * Every chat message should contain some kind of text. Also used for searches in database on client
     */
    lateinit var text: String
    var timeSent: Long = 0
    var id: UUID = UUID.randomUUID()

    private var aesKey: String = ""

    constructor(senderUUID: UUID, text: String, timeSent: Long) {
        this.senderUUID = senderUUID
        this.timeSent = timeSent
        this.text = text
    }

    constructor()


    /**
     * Additional info is used for database storing, all the information that is not defined in ChatMessage should be written in additional info. <br>
     * This gets written into file on the server and can only be gathered by requesting the reference
     */
    abstract fun processAdditionalInfo(array: ByteArray?)

    abstract fun getAdditionalInfo(): ByteArray?

    fun encrypt(key: Key) {
        val sKey = generateAESKey()
        text = text.encryptAES(sKey)
        aesKey = String(sKey.encoded).encryptRSA(key)
    }

    fun decrypt(key: Key) {
        aesKey = aesKey.decryptRSA(key)
        val sKey = aesKey.toAESKey()
        text = text.decryptAES(sKey)
    }
}