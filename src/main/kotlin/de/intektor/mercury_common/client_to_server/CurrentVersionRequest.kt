package de.intektor.mercury_common.client_to_server

/**
 * @author Intektor
 */
class CurrentVersionRequest(val currentVersionCode: Long) {
    companion object {
        val TARGET = "currentVersion"
    }
}