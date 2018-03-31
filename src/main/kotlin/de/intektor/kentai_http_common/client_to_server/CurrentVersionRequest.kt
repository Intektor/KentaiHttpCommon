package de.intektor.kentai_http_common.client_to_server

/**
 * @author Intektor
 */
class CurrentVersionRequest(val currentVersionCode: Long) {
    companion object {
        val TARGET = "currentVersion"
    }
}