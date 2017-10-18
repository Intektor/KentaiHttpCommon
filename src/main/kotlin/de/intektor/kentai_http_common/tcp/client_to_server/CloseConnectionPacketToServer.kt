package de.intektor.kentai_http_common.tcp.client_to_server

import de.intektor.kentai_http_common.tcp.IPacket
import java.io.DataInputStream
import java.io.DataOutputStream

/**
 * @author Intektor
 */
class CloseConnectionPacketToServer : IPacket {

    constructor()

    override fun write(output: DataOutputStream) {
    }

    override fun read(input: DataInputStream) {
    }
}