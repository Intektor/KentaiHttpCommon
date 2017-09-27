package de.intektor.kentai_http_common.chat.group_modification

import java.io.DataInputStream
import java.io.DataOutputStream
import java.security.Key

/**
 * @author Intektor
 */
abstract class GroupModification {

    lateinit var chatUUID: String

    constructor(chatUUID: String) {
        this.chatUUID = chatUUID
    }

    constructor()

    abstract fun encryptModification(aesKey: Key, initVector: ByteArray)

    abstract fun decryptModification(aesKey: Key, initVector: ByteArray)

    fun write(output: DataOutputStream) {
        output.writeUTF(chatUUID)
        writeToStream(output)
    }

    fun read(input: DataInputStream) {
        chatUUID = input.readUTF()
        readFromStream(input)
    }

    abstract fun writeToStream(output: DataOutputStream)

    abstract fun readFromStream(input: DataInputStream)
}