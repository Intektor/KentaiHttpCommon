package de.intektor.mercury_common.tcp

import java.net.Socket

/**
 * @author Intektor
 */
interface IPacketHandler<in T : IPacket> {

    fun handlePacket(packet: T, socketFrom: Socket)
}