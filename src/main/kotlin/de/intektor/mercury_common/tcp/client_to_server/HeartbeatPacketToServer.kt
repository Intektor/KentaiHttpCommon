package de.intektor.mercury_common.tcp.client_to_server

import de.intektor.mercury_common.tcp.IPacket
import java.io.DataInputStream
import java.io.DataOutputStream

/**
 * @author Intektor
 */
class HeartbeatPacketToServer : IPacket {

    constructor()

    override fun write(output: DataOutputStream) {
    }

    override fun read(input: DataInputStream) {
    }

}