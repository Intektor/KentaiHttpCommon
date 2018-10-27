package de.intektor.mercury_common.server_to_client

import java.util.*

/**
 * If userUUID == null then it hasn't worked
 * @author Intektor
 */
class RegisterRequestResponseToClient(val username: String, val userUUID: UUID?, val type: Type) {
    enum class Type {
        TAKEN_USERNAME,
        INVALID_USERNAME,
        SUCCESS
    }
}
