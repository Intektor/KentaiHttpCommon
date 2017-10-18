package de.intektor.kentai_http_common.tcp.client_to_server

import de.intektor.kentai_http_common.tcp.IPacket
import de.intektor.kentai_http_common.util.readUUID
import de.intektor.kentai_http_common.util.writeUUID
import java.io.DataInputStream
import java.io.DataOutputStream
import java.util.*

/**
 * This packet should be sent after a connection to the server is established.
 * This packet tells the server who the client is.
 * This packet needs an encrypted user UUID to verify that the user you pretend to be really is you.
 * The encryptedUserUUID should be encrypted with the private login key
 * @author Intektor
 */
class IdentificationPacketToServer : IPacket {

    lateinit var encryptedUserUUID: String
    lateinit var userUUID: UUID

    constructor(encryptedUserUUID: String, userUUID: UUID) {
        this.encryptedUserUUID = encryptedUserUUID
        this.userUUID = userUUID
    }

    constructor()

    override fun write(output: DataOutputStream) {
        output.writeUTF(encryptedUserUUID)
        output.writeUUID(userUUID)
    }

    override fun read(input: DataInputStream) {
        encryptedUserUUID = input.readUTF()
        userUUID = input.readUUID()
    }

}