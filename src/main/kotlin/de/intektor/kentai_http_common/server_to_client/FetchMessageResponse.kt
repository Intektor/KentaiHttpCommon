package de.intektor.kentai_http_common.server_to_client

/**
 * @see de.intektor.kentai_http_common.chat.ChatMessage.processAdditionalInfo
 * @author Intektor
 */
class FetchMessageResponse(val text: String, val referenceUUID: String, val timeSent: Long, val aesKey: String, val initVector: String, val smallAdditionalInfo: String?, val signature: String)