package de.intektor.kentai_http_common.util

import java.io.DataInputStream
import java.io.DataOutputStream
import java.util.*

/**
 * @author Intektor
 */
fun String.toUUID(): UUID = UUID.fromString(this)

fun String.minString(range: IntRange): String = this.substring(range.start..Math.min(this.length - 1, range.last))

fun UUID.write(output: DataOutputStream) {
    output.writeLong(this.mostSignificantBits)
    output.writeLong(this.leastSignificantBits)
}

fun DataInputStream.readUUID() = UUID(readLong(), readLong())