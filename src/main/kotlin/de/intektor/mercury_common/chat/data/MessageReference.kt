package de.intektor.mercury_common.chat.data

import de.intektor.mercury_common.chat.MessageData
import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
abstract class MessageReference(val aesKey: Key, val initVector: ByteArray, val reference: UUID, val hash: String) : MessageData