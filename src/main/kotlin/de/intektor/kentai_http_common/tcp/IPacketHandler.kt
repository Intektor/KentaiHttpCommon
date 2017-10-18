package de.intektor.kentai_http_common.tcp

import java.net.Socket

/**
 * @author Intektor
 */
interface IPacketHandler<in T : IPacket> {

    fun handlePacket(packet: T, socketFrom: Socket)
}