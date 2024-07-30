package com.diberardino.jbomb.network.messages

import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.models.HttpActor
import com.diberardino.jbomb.network.models.HttpMessage
import com.diberardino.jbomb.network.models.HttpMessageTypes
import com.diberardino.jbomb.utility.Extensions.toMap

class SpawnedEntityHttpMessage(private val entity: EntityNetwork, private val extras: Map<String, String> = hashMapOf()): HttpMessage {
    override fun serialize(): MutableMap<String, String> {
        val data: MutableMap<String, String> = HashMap()
        data["messageType"] = HttpMessageTypes.SPAWNED_ENTITY.ordinal.toString()
        data["entityId"] = entity.entityId.toString()
        data["entityType"] = entity.entityType.toString()
        data["location"] = "${entity.entityLocation!!.x} ${entity.entityLocation.y}"
        data.putAll(extras)
        data.putAll(entity.toMap())
        return data
    }

    override val senders: Array<HttpActor>
        get() = arrayOf(HttpActor.SERVER)
}