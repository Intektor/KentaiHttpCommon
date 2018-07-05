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
class GroupModificationChangePicture : GroupModification {

    lateinit var referenceUUID: String

    constructor(referenceUUID: UUID, chatUUID: String, modificationUUID: String) : super(chatUUID, modificationUUID) {
        this.referenceUUID = referenceUUID.toString()
    }

    constructor() : super()

    override fun encryptModification(aesKey: Key, initVector: ByteArray) {
        referenceUUID = referenceUUID.encryptAES(aesKey, initVector)
    }

    override fun decryptModification(aesKey: Key, initVector: ByteArray) {
        referenceUUID = referenceUUID.decryptAES(aesKey, initVector)
    }

    override fun writeToStream(output: DataOutputStream) {
        output.writeUTF(referenceUUID)
    }

    override fun readFromStream(input: DataInputStream) {
        referenceUUID = input.readUTF()
    }

}