package de.intektor.mercury_common.server_to_client

import java.security.interfaces.RSAPublicKey
import java.util.*

/**
 * @author Intektor
 */
class UserDataResponse(val data: List<UserData>) {

    data class UserData(val username: String, val userUUID: UUID, val key: RSAPublicKey)
}