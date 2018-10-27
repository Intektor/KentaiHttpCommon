package de.intektor.mercury_common.server_to_client

/**
 * @author Intektor
 */
class CheckUsernameAvailableResponseToClient(val available: Boolean) {
    companion object {
        val TARGET = "checkUsernameAvailable"
    }
}