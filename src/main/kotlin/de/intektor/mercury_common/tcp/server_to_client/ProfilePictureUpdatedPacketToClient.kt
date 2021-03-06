package de.intektor.mercury_common.tcp.server_to_client

import de.intektor.mercury_common.tcp.IPacket
import de.intektor.mercury_common.util.readUUID
import de.intektor.mercury_common.util.writeUUID
import java.io.DataInputStream
import java.io.DataOutputStream
import java.util.*

/**
 * @author Intektor
 */
class ProfilePictureUpdatedPacketToClient : IPacket {

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