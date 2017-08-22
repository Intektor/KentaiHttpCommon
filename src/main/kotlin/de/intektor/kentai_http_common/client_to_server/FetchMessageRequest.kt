package de.intektor.kentai_http_common.client_to_server

import java.util.*

/**
 * @author Intektor
 */
class FetchMessageRequest(val chatUUID: UUID, val encryptedMessageUUID: String, val userUUID: UUID) {
    companion object {
        val TARGET: String = "fetchMessage"
    }
}