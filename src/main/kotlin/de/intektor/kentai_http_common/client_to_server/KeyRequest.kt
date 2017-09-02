package de.intektor.kentai_http_common.client_to_server

import java.util.*

/**
 * @author Intektor
 */
class KeyRequest(val requestedUUIDs: List<UUID>) {
    companion object {
        val TARGET: String = "keyRequest"
    }
}