package de.intektor.mercury_common.chat

import java.util.*

/**
 * @author Intektor
 */
data class MessageCore(val senderUUID: UUID, val timeCreated: Long, val messageUUID: UUID)