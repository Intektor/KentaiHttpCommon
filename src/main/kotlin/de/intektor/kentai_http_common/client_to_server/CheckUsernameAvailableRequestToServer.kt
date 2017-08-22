package de.intektor.kentai_http_common.client_to_server

/**
 * @author Intektor
 */
class CheckUsernameAvailableRequestToServer(val username: String) {
    companion object {
        val TARGET = "check_username_available"
    }
}