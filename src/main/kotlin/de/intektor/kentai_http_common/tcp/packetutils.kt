package de.intektor.kentai_http_common.tcp

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

/**
 * @author Intektor
 */
fun sendPacket(packet: IPacket, outputStream: DataOutputStream) {
    outputStream.writeInt(KentaiTCPOperator.packetRegistry.getID(packet.javaClass))
    packet.write(outputStream)
}

fun readPacket(inputStream: DataInputStream, packetRegistry: PacketRegistry, side: Side): IPacket {
    val packetID = inputStream.readInt()
    if (packetRegistry.getTarget(packetID) != side) throw RuntimeException("Wrong side for packet!")
    val packet = KentaiTCPOperator.packetRegistry.fromID(packetID)
    packet.read(inputStream)
    return packet
}

fun handlePacket(packet: IPacket, socketFrom: Socket) {
    KentaiTCPOperator.packetRegistry.getHandler(KentaiTCPOperator.packetRegistry.getID(packet.javaClass)).handlePacket(packet, socketFrom)
}