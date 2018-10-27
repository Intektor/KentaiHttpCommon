package de.intektor.mercury_common.client_to_server

import java.util.*

/**
 * @author Intektor
 */
class UsersRequest(val users: List<UUID>) {
    companion object {
        const val TARGET = "usersRequest"
    }
}