package de.intektor.mercury_common.client_to_server

/**
 * @author Intektor
 */
class AddContactRequest(val username: String) {
    companion object {
        val TARGET = "add_contact_request"
    }
}