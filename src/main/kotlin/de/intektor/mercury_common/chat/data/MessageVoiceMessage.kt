package de.intektor.mercury_common.chat.data

import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
class MessageVoiceMessage(val durationSeconds: Int, aesKey: Key, initVector: ByteArray, reference: UUID, hash: String) : MessageReference(aesKey, initVector, reference, hash)