package de.intektor.mercury_common.chat

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import de.intektor.mercury_common.chat.data.*
import de.intektor.mercury_common.chat.data.group_modification.MessageGroupModification

/**
 * @author Intektor
 */
object MessageDataRegistry {

    private val registry: BiMap<Int, Class<out MessageData>> = HashBiMap.create()

    init {
        register(MessageText::class.java)
        register(MessageStatusUpdate::class.java)
        register(MessageGroupInvite::class.java)
        register(MessageGroupModification::class.java)
        register(MessageVoiceMessage::class.java)
        register(MessageImage::class.java)
        register(MessageVideo::class.java)
    }

    private fun register(clazz: Class<out MessageData>) {
        registry[registry.size] = clazz
    }

    fun getID(clazz: Class<out MessageData>): Int? = registry.inverse()[clazz]

    fun getClass(ID: Int): Class<out MessageData>? = registry[ID]

    fun getRegisteredClasses() = registry.values
}