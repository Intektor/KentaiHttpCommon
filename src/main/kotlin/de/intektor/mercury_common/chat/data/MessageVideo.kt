package de.intektor.mercury_common.chat.data

import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
class MessageVideo(val durationInSeconds: Int, val isGif: Boolean, val width: Int, val height: Int, val thumbnail: ByteArray, val text: String, aesKey: Key, initVector: ByteArray, reference: UUID, hash: String) : MessageReference(aesKey, initVector, reference, hash)