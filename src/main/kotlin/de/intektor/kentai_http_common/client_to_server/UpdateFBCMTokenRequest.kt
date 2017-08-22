package de.intektor.kentai_http_common.client_to_server

import java.util.*

/**
 * @author Intektor
 */
class UpdateFBCMTokenRequest(val userUUID: UUID, val encryptedToken: String) {
    companion object {
        val TARGET = "updateFBCMToken"
    }
}