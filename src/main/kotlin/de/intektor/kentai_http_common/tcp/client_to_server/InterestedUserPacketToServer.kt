package de.intektor.kentai_http_common.tcp.client_to_server

import de.intektor.kentai_http_common.tcp.IPacket
import de.intektor.kentai_http_common.util.readUUID
import de.intektor.kentai_http_common.util.writeUUID
import java.io.DataInputStream
import java.io.DataOutputStream

/**
 * @author Intektor
 */
class InterestedUserPacketToServer : IPacket {

    lateinit var interestedUser: InterestedUser
    var interested = false


    constructor(interestedUser: InterestedUser, interested: Boolean) {
        this.interestedUser = interestedUser
        this.interested = interested
    }

    constructor()

    override fun write(output: DataOutputStream) {
        output.writeUUID(interestedUser.userUUID)
        output.writeLong(interestedUser.lastTimeProfilePictureUpdate)
        output.writeBoolean(interested)
    }

    override fun read(input: DataInputStream) {
        interestedUser = InterestedUser(input.readUUID(), input.readLong())
        interested = input.readBoolean()
    }
}