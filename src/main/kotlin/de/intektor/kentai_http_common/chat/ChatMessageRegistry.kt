package de.intektor.kentai_http_common.chat

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap

/**
 * @author Intektor
 */
object ChatMessageRegistry {
    private val registry: BiMap<Int, Class<out ChatMessage>> = HashBiMap.create()

    init {
        register(ChatMessageText::class.java)
    }

    fun register(clazz: Class<out ChatMessage>) {
        registry.put(registry.size, clazz)
    }

    fun getID(clazz: Class<out ChatMessage>): Int = registry.inverse()[clazz]!!

    fun create(ID: Int): ChatMessage = registry[ID]!!.newInstance()
}