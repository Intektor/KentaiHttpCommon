package de.intektor.mercury_common.client_to_server

import java.security.interfaces.RSAPublicKey

/**
 * @author Intektor
 */
class RegisterRequestToServer(val username: String, val authKey: RSAPublicKey, val messageKey: RSAPublicKey, val fCMToken: String?) {
    companion object {
        val TARGET = "register"
    }
}