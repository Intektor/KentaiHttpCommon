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
class UserStatusChangePacketToClient : IPacket {

    lateinit var list: MutableList<UserChange>

    constructor(list: MutableList<UserChange>) {
        this.list = list
    }

    constructor()

    override fun write(output: DataOutputStream) {
        output.writeInt(list.size)
        for ((userUUID, status, time) in list) {
            output.writeUUID(userUUID)
            output.writeInt(status.ordinal)
            output.writeLong(time)
        }
    }

    override fun read(input: DataInputStream) {
        list = mutableListOf()

        val size = input.readInt()
        for (i in 0 until size) {
            list.add(UserChange(input.readUUID(), Status.values()[input.readInt()], input.readLong()))
        }
    }
}

data class UserChange(val userUUID: UUID, val status: Status, val time: Long)

enum class Status {
    ONLINE,
    OFFLINE_CLOSED,
    OFFLINE_EXCEPTION
}