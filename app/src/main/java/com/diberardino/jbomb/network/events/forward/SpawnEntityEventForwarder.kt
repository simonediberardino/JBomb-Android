package com.diberardino.jbomb.network.events.forward

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.network.dispatch.HttpMessageDispatcher
import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.messages.SpawnedEntityHttpMessage

class SpawnEntityEventForwarder(private val clientId: Long) : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as EntityNetwork
        HttpMessageDispatcher.instance.dispatch(SpawnedEntityHttpMessage(info), clientId)
    }
}