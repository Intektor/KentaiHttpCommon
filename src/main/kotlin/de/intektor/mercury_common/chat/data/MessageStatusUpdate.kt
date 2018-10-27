package de.intektor.mercury_common.chat.data

import de.intektor.mercury_common.chat.MessageData
import de.intektor.mercury_common.chat.MessageStatus
import java.util.*

/**
 * @author Intektor
 */
class MessageStatusUpdate(val messageUUID: UUID, val status: MessageStatus, val timeOfOccurence: Long) : MessageData