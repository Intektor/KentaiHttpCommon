package de.intektor.mercury_common.client_to_server

import de.intektor.mercury_common.users.ProfilePictureType
import java.util.*

/**
 * @author Intektor
 */
class DownloadProfilePictureRequest(val userUUID: UUID, val type: ProfilePictureType) {
    companion object {
        const val TARGET = "downloadProfilePicture"
    }
}