package de.intektor.mercury_common.tcp

import de.intektor.mercury_common.tcp.client_to_server.*
import de.intektor.mercury_common.tcp.server_to_client.*

/**
 * @author Intektor
 */
object MercuryTCPOperator {

    val packetRegistry: PacketRegistry = PacketRegistry()

    init {
        packetRegistry.registerPacket(IdentificationPacketToServer::class.java, Side.SERVER, 0)
        packetRegistry.registerPacket(CloseConnectionPacketToServer::class.java, Side.SERVER, 1)
        packetRegistry.registerPacket(UserPreferencePacketToServer::class.java, Side.SERVER, 2)
        packetRegistry.registerPacket(InterestedUserPacketToServer::class.java, Side.SERVER, 3)
        packetRegistry.registerPacket(UserStatusChangePacketToClient::class.java, Side.CLIENT, 4)
        packetRegistry.registerPacket(HeartbeatPacketToServer::class.java, Side.SERVER, 5)
        packetRegistry.registerPacket(HeartbeatPacketToClient::class.java, Side.CLIENT, 6)
        packetRegistry.registerPacket(TypingPacketToServer::class.java, Side.SERVER, 7)
        packetRegistry.registerPacket(TypingPacketToClient::class.java, Side.CLIENT, 8)
        packetRegistry.registerPacket(ViewChatPacketToServer::class.java, Side.SERVER, 9)
        packetRegistry.registerPacket(UserViewChatPacketToClient::class.java, Side.CLIENT, 10)
        packetRegistry.registerPacket(ProfilePictureUpdatedPacketToClient::class.java, Side.CLIENT, 11)
    }
}