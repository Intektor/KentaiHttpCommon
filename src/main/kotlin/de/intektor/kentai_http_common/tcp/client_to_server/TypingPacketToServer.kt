package de.intektor.kentai_http_common.tcp.client_to_server

import de.intektor.kentai_http_common.tcp.IPacket
import de.intektor.kentai_http_common.util.readUUID
import de.intektor.kentai_http_common.util.writeUUID
import java.io.DataInputStream
import java.io.DataOutputStream
import java.util.*

/**
 * @author Intektor
 */
class TypingPacketToServer : IPacket {

    lateinit var sendTo: List<UUID>
    lateinit var chatUUID: UUID

    constructor(sendTo: List<UUID>, chatUUID: UUID) {
        this.sendTo = sendTo
        this.chatUUID = chatUUID
    }

    constructor()

    override fun write(output: DataOutputStream) {
        output.writeInt(sendTo.size)
        for (uuid in sendTo) {
            output.writeUUID(uuid)
        }
        output.writeUUID(chatUUID)
    }

    override fun read(input: DataInputStream) {
        val lList = mutableListOf<UUID>()
        for (i in 0 until input.readInt()) {
            lList += input.readUUID()
        }
        sendTo = lList
        chatUUID = input.readUUID()
    }
}