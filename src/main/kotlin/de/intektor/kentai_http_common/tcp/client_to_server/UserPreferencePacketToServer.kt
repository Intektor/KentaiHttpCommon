package de.intektor.kentai_http_common.tcp.client_to_server

import de.intektor.kentai_http_common.tcp.IPacket
import de.intektor.kentai_http_common.util.readUUID
import de.intektor.kentai_http_common.util.writeUUID
import java.io.DataInputStream
import java.io.DataOutputStream
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author Intektor
 */
class UserPreferencePacketToServer : IPacket {

    lateinit var list: MutableList<UUID>

    constructor(list: MutableList<UUID>) {
        this.list = list
    }

    constructor()

    override fun write(output: DataOutputStream) {
        output.writeInt(list.size)
        for (uuid in list) {
            output.writeUUID(uuid)
        }
    }

    override fun read(input: DataInputStream) {
        val length = input.readInt()
        list = ArrayList(length)
        for (i in 0 until length) {
            list.add(input.readUUID())
        }
    }
}