package de.intektor.kentai_http_common.tcp

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap

/**
 * @author Intektor
 */
class PacketRegistry {

    private val idRegistry: BiMap<Class<out IPacket>, Int> = HashBiMap.create()
    private val handlerRegistry: MutableMap<Int, IPacketHandler<*>> = HashMap()
    private val targetRegistry: MutableMap<Int, Side> = HashMap()

    fun registerPacket(clazz: Class<out IPacket>, target: Side, id: Int) {
        idRegistry[clazz] = id
        targetRegistry[id] = target
    }

    fun <T : IPacket> registerHandler(clazz: Class<T>, handler: IPacketHandler<T>) {
        registerHandler(idRegistry[clazz]!!, handler)
    }

    fun registerHandler(id: Int, handler: IPacketHandler<*>) {
        handlerRegistry.put(id, handler)
    }

    fun getID(clazz: Class<out IPacket>): Int = idRegistry[clazz]!!

    fun getHandler(id: Int): IPacketHandler<IPacket> = handlerRegistry[id]!! as IPacketHandler<IPacket>

    fun getTarget(id: Int): Side = targetRegistry[id]!!

    fun fromID(id: Int): IPacket = idRegistry.inverse()[id]!!.newInstance()
}