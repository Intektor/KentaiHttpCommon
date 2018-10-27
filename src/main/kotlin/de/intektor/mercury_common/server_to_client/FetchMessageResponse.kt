package de.intektor.mercury_common.server_to_client

import java.util.*

@Suppress("ArrayInDataClass")
/**
 * @author Intektor
 */
class FetchMessageResponse(val list: List<Message>) {
    data class Message(val message: String, val messageUUID: UUID, val aesKey: String, val initVector: String, val chatUUID: String, val senderUUID: UUID, val signature: ByteArray)
}