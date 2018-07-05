package de.intektor.kentai_http_common.util

import java.security.Key

/**
 * @author Intektor
 */
interface Cryptable {

    fun encrypt(aesKey: Key, initVector: ByteArray)

    fun decrypt(aesKey: Key, initVector: ByteArray)
}