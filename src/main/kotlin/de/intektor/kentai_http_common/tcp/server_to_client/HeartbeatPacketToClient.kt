package de.intektor.kentai_http_common.tcp.server_to_client

import de.intektor.kentai_http_common.tcp.IPacket
import java.io.DataInputStream
import java.io.DataOutputStream

/**
 * @author Intektor
 */
class HeartbeatPacketToClient : IPacket {

    override fun write(output: DataOutputStream) {
    }

    override fun read(input: DataInputStream) {
    }
}