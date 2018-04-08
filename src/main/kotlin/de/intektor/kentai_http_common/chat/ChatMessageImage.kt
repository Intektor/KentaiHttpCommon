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
class ChatMessageImage : ChatMessage {

    lateinit var hash: String
    lateinit var smallPreview: ByteArray

    constructor(hash: String, senderUUID: UUID, text: String, timeSent: Long, smallPreview: ByteArray) : super(senderUUID, text, timeSent) {
        this.hash = hash
        this.smallPreview = smallPreview
    }

    constructor()

    override fun processAdditionalInfo(array: ByteArray?) {
        val byteIn = ByteArrayInputStream(array)
        val dataIn = DataInputStream(byteIn)
        hash = dataIn.readUTF()
        smallPreview = ByteArray(dataIn.readInt())
        dataIn.read(smallPreview)
    }

    override fun getAdditionalInfo(): ByteArray? {
        val byteOut = ByteArrayOutputStream()
        val dataOut = DataOutputStream(byteOut)
        dataOut.writeUTF(hash)
        dataOut.writeInt(smallPreview.size)
        dataOut.write(smallPreview)
        return byteOut.toByteArray()
    }

    override fun encryptMessage(aesKey: Key, initVector: ByteArray) {
        smallPreview = smallPreview.encryptAES(aesKey, initVector)
    }

    override fun decryptMessage(aesKey: Key, initVector: ByteArray) {
        smallPreview = smallPreview.decryptAES(aesKey, initVector)
    }

    override fun shouldBeStored(): Boolean = true

    override fun isSmallData(): Boolean = true

    override fun hasReference(): Boolean = true

}