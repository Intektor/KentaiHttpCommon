package de.intektor.kentai_http_common.client_to_server

/**
 * @author Intektor
 */
class HandledMessagesRequest(val messageList: List<String>, val encryptedUserUUID: String, val userUUID: String) {
    companion object {
        const val TARGET = "handledMessages"
    }
}