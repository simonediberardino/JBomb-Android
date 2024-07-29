package com.diberardino.jbomb.network.messages

import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.models.HttpActor
import com.diberardino.jbomb.network.models.HttpMessage
import com.diberardino.jbomb.network.models.HttpMessageTypes

class AttackEntityHttpMessage(private val entity: EntityNetwork, private val damage: Int): HttpMessage {
    override fun serialize(): MutableMap<String, String> {
        val data: MutableMap<String, String> = HashMap()
        data["messageType"] = HttpMessageTypes.ENTITY_ATTACKED.ordinal.toString()
        data["entityId"] = entity.entityId.toString()
        data["damage"] = damage.toString()
        return data
    }

    override val senders: Array<HttpActor> = arrayOf(HttpActor.SERVER)
}