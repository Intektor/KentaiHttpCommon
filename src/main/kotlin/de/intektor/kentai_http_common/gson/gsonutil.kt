package de.intektor.kentai_http_common.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * @author Intektor
 */
fun genGson(): Gson {
    val builder = GsonBuilder()
    return builder.create()
}