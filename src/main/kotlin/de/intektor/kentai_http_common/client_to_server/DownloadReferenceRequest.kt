package de.intektor.kentai_http_common.client_to_server

import java.util.*

/**
 * @author Intektor
 */
class DownloadReferenceRequest(val referenceUUID: UUID) {
    companion object {
        val TARGET = "downloadReference"
    }

    enum class Response {
        NOT_FOUND,
        DELETED,
        SUCCESS
    }
}