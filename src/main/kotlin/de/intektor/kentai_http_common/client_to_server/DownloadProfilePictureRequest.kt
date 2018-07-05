package de.intektor.kentai_http_common.client_to_server

import de.intektor.kentai_http_common.users.ProfilePictureType
import java.util.*

/**
 * @author Intektor
 */
class DownloadProfilePictureRequest(val userUUID: UUID, val type: ProfilePictureType) {
    companion object {
        const val TARGET = "downloadProfilePicture"
    }
}