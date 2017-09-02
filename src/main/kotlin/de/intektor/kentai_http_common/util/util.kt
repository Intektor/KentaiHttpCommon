package de.intektor.kentai_http_common.util

import java.util.*

/**
 * @author Intektor
 */
fun String.toUUID(): UUID = UUID.fromString(this)

fun String.minString(range: IntRange): String = this.substring(range.start..Math.min(this.length - 1, range.last))