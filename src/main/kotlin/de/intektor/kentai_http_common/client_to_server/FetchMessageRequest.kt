package de.intektor.kentai_http_common.client_to_server

/**
 * @author Intektor
 */
class FetchMessageRequest(val userUUID: String, val encryptedUserUUID: String) {
    companion object {
        val TARGET: String = "fetchMessage"
    }
}