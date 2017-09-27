package de.intektor.kentai_http_common.chat.group_modification

import de.intektor.kentai_http_common.util.decryptAES
import de.intektor.kentai_http_common.util.encryptAES
import java.io.DataInputStream
import java.io.DataOutputStream
import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
class GroupModificationAddUser : GroupModification {

    lateinit var userUUID: String

    constructor(userUUID: UUID, chatUUID: String) : super(chatUUID) {
        this.userUUID = userUUID.toString()
    }

    constructor(chatUUID: String) : super(chatUUID)

    override fun encryptModification(aesKey: Key, initVector: ByteArray) {
        userUUID = userUUID.encryptAES(aesKey, initVector)
    }

    override fun decryptModification(aesKey: Key, initVector: ByteArray) {
        userUUID = userUUID.decryptAES(aesKey, initVector)
    }

    override fun writeToStream(output: DataOutputStream) {
        output.writeUTF(userUUID)
    }

    override fun readFromStream(input: DataInputStream) {
        userUUID = input.readUTF()
    }
}