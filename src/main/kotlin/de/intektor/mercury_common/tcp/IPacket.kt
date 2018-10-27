package de.intektor.mercury_common.tcp

import java.io.DataInputStream
import java.io.DataOutputStream

/**
 * @author Intektor
 */
interface IPacket {

    fun write(output: DataOutputStream)

    fun read(input: DataInputStream)
}