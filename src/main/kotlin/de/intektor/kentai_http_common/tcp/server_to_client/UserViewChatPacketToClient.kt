package de.intektor.kentai_http_common.tcp.server_to_client

import de.intektor.kentai_http_common.tcp.IPacket
import de.intektor.kentai_http_common.util.readUUID
import de.intektor.kentai_http_common.util.writeUUID
import java.io.DataInputStream
import java.io.DataOutputStream
import java.util.*

/**
 * @author Intektor
 */
class UserViewChatPacketToClient : IPacket {

    lateinit var userUUID: UUID
    lateinit var chatUUID: UUID
    var view: Boolean = false

    constructor(userUUID: UUID, chatUUID: UUID, view: Boolean) {
        this.userUUID = userUUID
        this.chatUUID = chatUUID
        this.view = view
    }

    constructor()

    override fun write(output: DataOutputStream) {
        output.writeUUID(userUUID)
        output.writeUUID(chatUUID)
        output.writeBoolean(view)
    }


    override fun read(input: DataInputStream) {
        userUUID = input.readUUID()
        chatUUID = input.readUUID()
        view = input.readBoolean()
    }

}