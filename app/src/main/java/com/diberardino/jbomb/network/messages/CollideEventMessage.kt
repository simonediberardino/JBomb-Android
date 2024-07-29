package com.diberardino.jbomb.network.messages

import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.models.HttpActor
import com.diberardino.jbomb.network.models.HttpMessage
import com.diberardino.jbomb.network.models.HttpMessageTypes

/**
 * Client sends a collide event to server: server will decide whether to interact, attack etc...
 */
class CollideEventMessage(private val victim: EntityNetwork, private val self: EntityNetwork) : HttpMessage {
    override fun serialize(): MutableMap<String, String> {
        val data: MutableMap<String, String> = HashMap()
        data["messageType"] = HttpMessageTypes.ENTITY_COLLIDED.ordinal.toString()
        data["victimId"] = victim.entityId.toString()
        data["selfId"] = self.entityId.toString()
        return data
    }

    override val senders: Array<HttpActor> = arrayOf(HttpActor.CLIENT)
}