package de.intektor.mercury_common.server_to_client

import java.security.interfaces.RSAPublicKey
import java.util.*

/**
 * @author Intektor
 */
class AddContactResponse(val isValid: Boolean, val messageKey: RSAPublicKey?, val userUUID: UUID)