package de.intektor.mercury_common.client_to_server

import java.util.*

/**
 * @param signature the signature of the token in UTF-8
 * @author Intektor
 */
class UpdateFBCMTokenRequest(val userUUID: UUID, val signature: ByteArray, val token: String) {
    companion object {
        val TARGET = "updateFBCMToken"
    }
}