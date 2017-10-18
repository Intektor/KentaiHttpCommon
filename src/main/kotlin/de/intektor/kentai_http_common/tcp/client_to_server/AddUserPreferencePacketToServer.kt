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
class AddUserPreferencePacketToServer : IPacket {

    lateinit var userUUID: UUID

    constructor(userUUID: UUID) {
        this.userUUID = userUUID
    }

    constructor()

    override fun write(output: DataOutputStream) {
        output.writeUUID(userUUID)
    }

    override fun read(input: DataInputStream) {
        userUUID = input.readUUID()
    }
}