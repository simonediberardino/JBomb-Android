package com.diberardino.jbomb.network.messages

import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.models.HttpActor
import com.diberardino.jbomb.network.models.HttpMessage
import com.diberardino.jbomb.network.models.HttpMessageTypes

import com.diberardino.jbomb.domain.world.domain.items.ItemsTypes

class UseItemHttpMessage(private val userDao: EntityNetwork, private val itemType: ItemsTypes, private val itemId: Long) : HttpMessage {
    override fun serialize(): MutableMap<String, String> {
        val data: MutableMap<String, String> = HashMap()
        data["messageType"] = HttpMessageTypes.USE_ITEM.ordinal.toString()
        data["entityId"] = userDao.entityId.toString()
        data["itemType"] = itemType.toInt().toString()
        data["itemId"] = itemId.toString()
        return data
    }

    override val senders: Array<HttpActor> = arrayOf(HttpActor.SERVER, HttpActor.CLIENT)
}