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
class GroupModificationKickUser : GroupModification {

    lateinit var toKick: String
    lateinit var reason: String

    constructor(toKick: UUID, reason: String, chatUUID: String) : super(chatUUID) {
        this.toKick = toKick.toString()
        this.reason = reason
    }

    constructor(chatUUID: String) : super(chatUUID)

    override fun encryptModification(aesKey: Key, initVector: ByteArray) {
        toKick = toKick.encryptAES(aesKey, initVector)
        reason = reason.encryptAES(aesKey, initVector)
    }

    override fun decryptModification(aesKey: Key, initVector: ByteArray) {
        toKick = toKick.decryptAES(aesKey, initVector)
        reason = reason.decryptAES(aesKey, initVector)
    }

    override fun writeToStream(output: DataOutputStream) {
        output.writeUTF(toKick)
        output.writeUTF(reason)
    }

    override fun readFromStream(input: DataInputStream) {
        toKick = input.readUTF()
        reason = input.readUTF()
    }
}