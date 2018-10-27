package de.intektor.mercury_common.chat.data

import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
class MessageImage(val thumbnail: ByteArray, val text: String, val width: Int, val height: Int, aesKey: Key, initVector: ByteArray, reference: UUID, hash: String) : MessageReference(aesKey, initVector, reference, hash)