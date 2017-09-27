package de.intektor.kentai_http_common.chat

import com.google.common.io.BaseEncoding
import de.intektor.kentai_http_common.util.decryptAES
import de.intektor.kentai_http_common.util.encryptAES
import java.io.*
import java.security.Key
import java.util.*
import javax.crypto.SecretKey

/**
 * @author Intektor
 */
class ChatMessageGroupInvite : ChatMessage {

    lateinit var chatUUID: String
    lateinit var groupName: String
    lateinit var roleMap: HashMap<UUID, GroupRole>
    lateinit var groupKey: String

    constructor(chatUUID: UUID, roleMap: HashMap<UUID, GroupRole>, groupName: String, groupKey: SecretKey, senderUUID: UUID, timeSent: Long) : super(senderUUID, "", timeSent) {
        this.chatUUID = chatUUID.toString()
        this.groupName = groupName
        this.roleMap = roleMap
        this.groupKey = BaseEncoding.base64().encode(groupKey.encoded)
    }

    constructor()

    override fun processAdditionalInfo(array: ByteArray?) {
        val byteIn = ByteArrayInputStream(array)
        val dataIn = DataInputStream(byteIn)
        val objIn = ObjectInputStream(byteIn)
        chatUUID = dataIn.readUTF()
        groupName = dataIn.readUTF()
        roleMap = objIn.readObject() as HashMap<UUID, GroupRole>
        groupKey = dataIn.readUTF()
    }

    override fun getAdditionalInfo(): ByteArray? {
        val byteOut = ByteArrayOutputStream()
        val dataOut = DataOutputStream(byteOut)
        val objOut = ObjectOutputStream(byteOut)
        dataOut.writeUTF(chatUUID)
        dataOut.writeUTF(groupName)
        objOut.writeObject(roleMap)
        dataOut.writeUTF(groupKey)
        return byteOut.toByteArray()
    }

    override fun encryptMessage(aesKey: Key, initVector: ByteArray) {
        chatUUID = chatUUID.encryptAES(aesKey, initVector)
        groupName = groupName.encryptAES(aesKey, initVector)
        groupKey = groupKey.encryptAES(aesKey, initVector)
    }

    override fun decryptMessage(aesKey: Key, initVector: ByteArray) {
        chatUUID = chatUUID.decryptAES(aesKey, initVector)
        groupName = groupName.decryptAES(aesKey, initVector)
        groupKey = groupKey.decryptAES(aesKey, initVector)
    }

    override fun shouldBeStored(): Boolean = true

    override fun isSmallData(): Boolean = true

    override fun hasReference(): Boolean = false
}