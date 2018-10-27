package de.intektor.mercury_common.tcp.server_to_client

import de.intektor.mercury_common.tcp.IPacket
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