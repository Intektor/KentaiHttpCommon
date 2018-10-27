package de.intektor.mercury_common.chat.data.group_modification

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import java.lang.reflect.Constructor
import java.util.*

/**
 * @author Intektor
 */
object GroupModificationRegistry {

    private val registry: BiMap<Int, Class<out GroupModification>> = HashBiMap.create()
    private val constructorMap: MutableMap<Int, Constructor<out GroupModification>> = HashMap()

    init {
        register(GroupModificationChangeName::class.java, 0)
        register(GroupModificationChangeRole::class.java, 1)
        register(GroupModificationKickUser::class.java, 2)
        register(GroupModificationAddUser::class.java, 3)
        register(GroupModificationChangePicture::class.java, 4)
    }

    private fun register(clazz: Class<out GroupModification>, id: Int) {
        registry[id] = clazz
        constructorMap[id] = clazz.getConstructor(String::class.java, String::class.java)
    }

    fun getID(clazz: Class<out GroupModification>): Int? = registry.inverse()[clazz]

    fun getClass(id: Int) = registry[id] ?: throw IllegalStateException("No registration found")
}