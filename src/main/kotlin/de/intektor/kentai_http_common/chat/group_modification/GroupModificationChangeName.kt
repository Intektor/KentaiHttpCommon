package de.intektor.kentai_http_common.chat.group_modification

import de.intektor.kentai_http_common.util.decryptAES
import de.intektor.kentai_http_common.util.encryptAES
import java.io.DataInputStream
import java.io.DataOutputStream
import java.security.Key

/**
 * @author Intektor
 */
class GroupModificationChangeName : GroupModification {

    lateinit var oldName: String
    lateinit var newName: String

    constructor(oldName: String, newName: String, chatUUID: String, modificationUUID: String) : super(chatUUID, modificationUUID) {
        this.oldName = oldName
        this.newName = newName
    }

    constructor(chatUUID: String, modificationUUID: String) : super(chatUUID, modificationUUID)

    override fun encryptModification(aesKey: Key, initVector: ByteArray) {
        newName.encryptAES(aesKey, initVector)
        oldName.encryptAES(aesKey, initVector)
    }

    override fun decryptModification(aesKey: Key, initVector: ByteArray) {
        oldName = oldName.decryptAES(aesKey, initVector)
        newName = newName.decryptAES(aesKey, initVector)
    }

    override fun writeToStream(output: DataOutputStream) {
        output.writeUTF(newName)
        output.writeUTF(oldName)
    }

    override fun readFromStream(input: DataInputStream) {
        newName = input.readUTF()
        oldName = input.readUTF()
    }
}