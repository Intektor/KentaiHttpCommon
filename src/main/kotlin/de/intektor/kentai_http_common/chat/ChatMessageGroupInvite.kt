package de.intektor.kentai_http_common.chat

import com.google.common.io.BaseEncoding
import de.intektor.kentai_http_common.util.Cryptable
import de.intektor.kentai_http_common.util.Serializable
import de.intektor.kentai_http_common.util.decryptAES
import de.intektor.kentai_http_common.util.encryptAES
import java.io.*
import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
class ChatMessageGroupInvite : ChatMessage {

    lateinit var groupInvite: GroupInvite

    constructor(groupInvite: GroupInvite, senderUUID: UUID, timeSent: Long) : super(senderUUID, "", timeSent) {
        this.groupInvite = groupInvite
    }

    constructor()

    override fun processAdditionalInfo(array: ByteArray?) {
        val byteIn = ByteArrayInputStream(array)
        val dataIn = DataInputStream(byteIn)
        val type = ChatType.values()[dataIn.readInt()]
        groupInvite = when (type) {
            ChatType.GROUP_CENTRALIZED -> GroupInviteCentralizedChat()
            ChatType.GROUP_DECENTRALIZED -> GroupInviteDecentralizedChat()
            else -> throw IllegalArgumentException()
        }
        groupInvite.read(dataIn)
    }

    override fun getAdditionalInfo(): ByteArray? {
        val byteOut = ByteArrayOutputStream()
        val dataOut = DataOutputStream(byteOut)
        dataOut.writeInt(if (groupInvite is GroupInviteCentralizedChat) ChatType.GROUP_CENTRALIZED.ordinal else ChatType.GROUP_DECENTRALIZED.ordinal)
        groupInvite.write(dataOut)
        return byteOut.toByteArray()
    }

    override fun encryptMessage(aesKey: Key, initVector: ByteArray) {
        groupInvite.encrypt(aesKey, initVector)
    }

    override fun decryptMessage(aesKey: Key, initVector: ByteArray) {
        groupInvite.decrypt(aesKey, initVector)
    }

    override fun shouldBeStored(): Boolean = true

    override fun isSmallData(): Boolean = true

    override fun hasReference(): Boolean = false

    abstract class GroupInvite : Serializable, Cryptable {

        lateinit var chatUUID: String
        lateinit var groupName: String
        lateinit var groupKey: String

        constructor()

        constructor(chatUUID: UUID, groupName: String, groupKey: Key) {
            this.chatUUID = chatUUID.toString()
            this.groupName = groupName
            this.groupKey = BaseEncoding.base64().encode(groupKey.encoded)
        }

        override fun read(input: DataInputStream) {
            chatUUID = input.readUTF()
            groupName = input.readUTF()
            groupKey = input.readUTF()
        }

        override fun write(output: DataOutputStream) {
            output.writeUTF(chatUUID)
            output.writeUTF(groupName)
            output.writeUTF(groupKey)
        }

        override fun encrypt(aesKey: Key, initVector: ByteArray) {
            chatUUID = chatUUID.encryptAES(aesKey, initVector)
            groupName = groupName.encryptAES(aesKey, initVector)
            groupKey = groupKey.encryptAES(aesKey, initVector)
        }

        override fun decrypt(aesKey: Key, initVector: ByteArray) {
            chatUUID = chatUUID.decryptAES(aesKey, initVector)
            groupName = groupName.decryptAES(aesKey, initVector)
            groupKey = groupKey.decryptAES(aesKey, initVector)
        }
    }

    class GroupInviteDecentralizedChat : GroupInvite {

        lateinit var roleMap: HashMap<UUID, GroupRole>

        constructor() : super()

        constructor(roleMap: HashMap<UUID, GroupRole>, chatUUID: UUID, groupName: String, groupKey: Key) : super(chatUUID, groupName, groupKey) {
            this.roleMap = roleMap
        }

        override fun write(output: DataOutputStream) {
            super.write(output)
            val objOut = ObjectOutputStream(output)
            objOut.writeObject(roleMap)
        }

        override fun read(input: DataInputStream) {
            super.read(input)

            val objIn = ObjectInputStream(input)
            roleMap = objIn.readObject() as HashMap<UUID, GroupRole>
        }
    }

    class GroupInviteCentralizedChat : GroupInvite {
        constructor() : super()

        constructor(chatUUID: UUID, groupName: String, groupKey: Key) : super(chatUUID, groupName, groupKey)
    }
}