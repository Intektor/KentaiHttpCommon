package de.intektor.kentai_http_common.chat.group_modification

import de.intektor.kentai_http_common.chat.ChatMessage
import de.intektor.kentai_http_common.util.readUUID
import de.intektor.kentai_http_common.util.toUUID
import de.intektor.kentai_http_common.util.write
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
class ChatMessageGroupModification : ChatMessage {

    lateinit var groupModification: GroupModification

    constructor(groupModification: GroupModification, senderUUID: UUID, timeSent: Long) : super(senderUUID, "", timeSent) {
        this.groupModification = groupModification
    }

    constructor()

    override fun encryptMessage(aesKey: Key, initVector: ByteArray) {
        groupModification.encryptModification(aesKey, initVector)
    }

    override fun decryptMessage(aesKey: Key, initVector: ByteArray) {
        groupModification.decryptModification(aesKey, initVector)
    }

    override fun processAdditionalInfo(array: ByteArray?) {
        val byteIn = ByteArrayInputStream(array)
        val dataIn = DataInputStream(byteIn)
        groupModification = GroupModificationRegistry.create(dataIn.readInt(), dataIn.readUUID(), dataIn.readUUID())
        groupModification.read(dataIn)
    }

    override fun getAdditionalInfo(): ByteArray? {
        val byteOut = ByteArrayOutputStream()
        val dataOut = DataOutputStream(byteOut)
        dataOut.writeInt(GroupModificationRegistry.getID(groupModification::class.java))
        groupModification.chatUUID.toUUID().write(dataOut)
        groupModification.modificationUUID.toUUID().write(dataOut)
        groupModification.write(dataOut)

        return byteOut.toByteArray()
    }

    override fun shouldBeStored(): Boolean = true

    override fun isSmallData(): Boolean = true

    override fun hasReference(): Boolean = false
}