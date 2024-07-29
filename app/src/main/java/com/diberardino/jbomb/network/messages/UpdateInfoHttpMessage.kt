package com.diberardino.jbomb.network.messages

import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.models.HttpActor
import com.diberardino.jbomb.network.models.HttpMessage
import com.diberardino.jbomb.network.models.HttpMessageTypes
import com.diberardino.jbomb.utils.dev.Extensions.toMap
import com.diberardino.jbomb.utils.dev.Log

class UpdateInfoHttpMessage(
        private val entityNetwork: EntityNetwork? = null,
        private val entityId: Long? = null,
        private val params: Map<String, String>? = null
) : HttpMessage {
    override fun serialize(): MutableMap<String, String> {
        val data: MutableMap<String, String> = HashMap()
        data["messageType"] = HttpMessageTypes.UPDATE_INFO.ordinal.toString()

        entityNetwork?.toMap()?.let {
            data.putAll(it)
        }

        entityId?.let { data["entityId"] = it.toString() }
        params?.let { data.putAll(it) }

        Log.i(this.javaClass.simpleName, "UpdateInfoHttpMessage $data")
        return data
    }

    override val senders: Array<HttpActor>
        get() = arrayOf(HttpActor.CLIENT, HttpActor.SERVER)
}