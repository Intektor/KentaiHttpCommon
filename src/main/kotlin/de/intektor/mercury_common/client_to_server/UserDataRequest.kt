package de.intektor.mercury_common.client_to_server

import java.util.*

/**
 * @author Intektor
 */
class UserDataRequest(val requestedUUIDs: List<UUID>) {
    companion object {
        val TARGET: String = "userDataRequest"
    }
}