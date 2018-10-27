package de.intektor.mercury_common.server_to_client

/**
 * @author Intektor
 */
class UploadProfilePictureResponse(val type: Type) {

    enum class Type {
        SUCCESS,
        FAILED_UNKNOWN_USER,
        FAILED_VERIFY_SIGNATURE
    }
}