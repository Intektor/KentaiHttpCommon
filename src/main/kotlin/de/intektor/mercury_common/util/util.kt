package de.intektor.mercury_common.util

import com.google.common.io.BaseEncoding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStream
import java.io.OutputStream
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

fun DataOutputStream.writeUUID(uuid: UUID) {
    this.writeLong(uuid.mostSignificantBits)
    this.writeLong(uuid.leastSignificantBits)
}


fun DataInputStream.readUUID() = UUID(readLong(), readLong())

fun InputStream.copyFully(out: OutputStream, bufferSize: Int = 1024 * 8) {
    val buffer = ByteArray(1024 * 8)
    while (true) {
        val read = this.read(buffer)
        if (read == -1) break
        out.write(buffer, 0, read)
    }
}

fun ByteArray.base64(): String = BaseEncoding.base64().encode(this)

fun <T> aswell(block: () -> T): T = block()