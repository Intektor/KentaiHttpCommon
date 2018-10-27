package de.intektor.mercury_common.reference

/**
 * @author Intektor
 */
enum class FileType(val extension: String) {
    AUDIO("3gp"),
    IMAGE("jpeg"),
    VIDEO("mp4"),
    GIF("gif");

    companion object {
        fun forExtension(extension: String) = FileType.values().firstOrNull { it.extension == extension }
    }
}