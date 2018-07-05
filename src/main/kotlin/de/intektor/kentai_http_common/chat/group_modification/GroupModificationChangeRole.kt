package de.intektor.kentai_http_common.chat.group_modification

import de.intektor.kentai_http_common.chat.GroupRole
import de.intektor.kentai_http_common.util.decryptAES
import de.intektor.kentai_http_common.util.encryptAES
import java.io.DataInputStream
import java.io.DataOutputStream
import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
class GroupModificationChangeRole : GroupModification {

    lateinit var userUUID: String
    lateinit var oldRole: String
    lateinit var newRole: String

    constructor(userUUID: UUID, oldRole: GroupRole, newRole: GroupRole, chatUUID: String, modificationUUID: String) : super(chatUUID, modificationUUID) {
        this.userUUID = userUUID.toString()
        this.oldRole = oldRole.ordinal.toString()
        this.newRole = newRole.ordinal.toString()
    }

    constructor(chatUUID: String, modificationUUID: String) : super(chatUUID, modificationUUID)

    override fun encryptModification(aesKey: Key, initVector: ByteArray) {
        userUUID = userUUID.encryptAES(aesKey, initVector)
        oldRole = oldRole.encryptAES(aesKey, initVector)
        newRole = newRole.encryptAES(aesKey, initVector)
    }

    override fun decryptModification(aesKey: Key, initVector: ByteArray) {
        userUUID = userUUID.decryptAES(aesKey, initVector)
        oldRole = oldRole.decryptAES(aesKey, initVector)
        newRole = newRole.decryptAES(aesKey, initVector)
    }

    override fun writeToStream(output: DataOutputStream) {
        output.writeUTF(userUUID)
        output.writeUTF(oldRole)
        output.writeUTF(newRole)
    }

    override fun readFromStream(input: DataInputStream) {
        userUUID = input.readUTF()
        oldRole = input.readUTF()
        newRole = input.readUTF()
    }
}