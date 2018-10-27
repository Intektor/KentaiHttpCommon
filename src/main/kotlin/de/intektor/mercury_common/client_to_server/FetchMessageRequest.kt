package de.intektor.mercury_common.client_to_server

import java.util.*

/**
 * @author Intektor
 */
class FetchMessageRequest(val userUUID: UUID, val signature: ByteArray) {
    companion object {
        val TARGET: String = "fetchMessage"
    }
}