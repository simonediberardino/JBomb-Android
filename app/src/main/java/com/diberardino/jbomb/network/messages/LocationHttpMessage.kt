package com.diberardino.jbomb.network.messages

import com.diberardino.jbomb.network.entity.CharacterNetwork
import com.diberardino.jbomb.network.models.HttpActor
import com.diberardino.jbomb.network.models.HttpMessage
import com.diberardino.jbomb.network.models.HttpMessageTypes

class LocationHttpMessage(private val character: CharacterNetwork, private val sentByClient: Boolean = false): HttpMessage {
    override fun serialize(): MutableMap<String, String> {
        val data: MutableMap<String, String> = HashMap()
        data["messageType"] = HttpMessageTypes.LOCATION.ordinal.toString()
        data["entityId"] = character.entityId.toString()
        data["location"] = "${character.entityLocation!!.x} ${character.entityLocation.y}"
        data["direction"] = character.direction.toString()
        data["sentByClient"] = sentByClient.toString()

        return data
    }

    override val senders: Array<HttpActor>
        get() = arrayOf(HttpActor.CLIENT, HttpActor.SERVER)
}