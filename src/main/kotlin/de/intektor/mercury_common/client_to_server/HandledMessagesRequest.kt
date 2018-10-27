package de.intektor.mercury_common.client_to_server

import java.util.*

/**
 * @param messageList the list of message uuids handled
 * @author Intektor
 */
class HandledMessagesRequest(val messageList: List<UUID>, val signature: ByteArray, val userUUID: UUID) {
    companion object {
        const val TARGET = "handledMessages"
    }
}