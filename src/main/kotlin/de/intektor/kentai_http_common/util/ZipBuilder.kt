package de.intektor.kentai_http_common.util

import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * @author Intektor
 */
class ZipBuilder(file: File, private val bufferSize: Int = 8 * 1024) : Closeable {

    val zipOut: ZipOutputStream = ZipOutputStream(BufferedOutputStream(FileOutputStream(file), bufferSize))

    fun addFile(file: File, folderName: String? = null, fileName: String = file.name): ZipBuilder {
        val finalName = if (folderName == null) fileName else "$folderName/$fileName"
        val fis = BufferedInputStream(FileInputStream(file))
        val entry = ZipEntry(finalName)
        zipOut.putNextEntry(entry)
        fis.copyTo(zipOut, bufferSize)
        fis.close()
        return this
    }

    override fun close() {
        zipOut.close()
    }

}