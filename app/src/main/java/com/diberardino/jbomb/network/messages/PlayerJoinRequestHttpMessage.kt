package com.diberardino.jbomb.network.messages

import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.models.HttpActor
import com.diberardino.jbomb.network.models.HttpMessage
import com.diberardino.jbomb.network.models.HttpMessageTypes
import com.diberardino.jbomb.utility.Extensions.toMap

class PlayerJoinRequestHttpMessage(private val id: Int, private val extra: EntityNetwork): HttpMessage {
    override fun serialize(): MutableMap<String, String> {
        val data: MutableMap<String, String> = HashMap()
        data["messageType"] = HttpMessageTypes.PLAYER_JOIN_REQUEST.ordinal.toString()
        data["id"] = id.toString()
        data.putAll(extra.toMap())
        return data
    }

    override val senders: Array<HttpActor>
        get() = arrayOf(HttpActor.CLIENT)
}