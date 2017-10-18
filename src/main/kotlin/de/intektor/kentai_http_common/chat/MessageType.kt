package de.intektor.kentai_http_common.chat

/**
 * @author Intektor
 */
enum class MessageType {
    TEXT_MESSAGE,
    MESSAGE_STATUS_CHANGE,
    GROUP_INVITE,
    GROUP_MODIFICATION,
    VOICE_MESSAGE,
    IMAGE_MESSAGE,
    VIDEO_MESSAGE,
    TYPING_MESSAGE
}