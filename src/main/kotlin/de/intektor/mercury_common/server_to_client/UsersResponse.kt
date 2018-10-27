package de.intektor.mercury_common.server_to_client

import java.security.interfaces.RSAPublicKey
import java.util.*

/**
 * @author Intektor
 */
class UsersResponse(val users: List<UserResponse>) {

    data class UserResponse(val userUUID: UUID, val username: String, val messageKey: RSAPublicKey)
}