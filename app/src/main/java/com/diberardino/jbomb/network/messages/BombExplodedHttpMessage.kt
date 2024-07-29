package com.diberardino.jbomb.network.messages

import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.models.HttpActor
import com.diberardino.jbomb.network.models.HttpMessage
import com.diberardino.jbomb.network.models.HttpMessageTypes
import com.diberardino.jbomb.utils.dev.Log

class BombExplodedHttpMessage(
        private val caller: EntityNetwork,
        private val bomb: EntityNetwork
): HttpMessage {
    override fun serialize(): MutableMap<String, String> {
        val data: MutableMap<String, String> = HashMap()
        data["messageType"] = HttpMessageTypes.BOMB_EXPLODED.ordinal.toString()
        data["entityId"] = caller.entityId.toString()
        data["bombId"] = bomb.entityId.toString()

        Log.e(this.javaClass.simpleName, "BombExplodedHttpMessage Sending $data")
        return data
    }

    override val senders: Array<HttpActor> = arrayOf(HttpActor.SERVER, HttpActor.CLIENT)
}