package de.intektor.kentai_http_common.util

import java.io.DataInputStream
import java.io.DataOutputStream

/**
 * @author Intektor
 */
interface Serializable {

    fun read(input: DataInputStream)

    fun write(output: DataOutputStream)
}