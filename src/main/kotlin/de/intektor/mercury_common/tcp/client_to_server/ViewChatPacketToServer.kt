package de.intektor.mercury_common.tcp.client_to_server

import de.intektor.mercury_common.tcp.IPacket
import de.intektor.mercury_common.util.readUUID
import de.intektor.mercury_common.util.writeUUID
import java.io.DataInputStream
import java.io.DataOutputStream
import java.util.*

/**
 * @author Intektor
 */
class ViewChatPacketToServer : IPacket {

    lateinit var chatUUID: UUID
    var view: Boolean = false

    constructor(chatUUID: UUID, view: Boolean) {
        this.chatUUID = chatUUID
        this.view = view
    }

    constructor()

    override fun write(output: DataOutputStream) {
        output.writeUUID(chatUUID)
        output.writeBoolean(view)
    }

    override fun read(input: DataInputStream) {
        chatUUID = input.readUUID()
        view = input.readBoolean()
    }

}