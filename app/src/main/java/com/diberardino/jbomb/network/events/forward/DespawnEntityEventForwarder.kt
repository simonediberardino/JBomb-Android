package com.diberardino.jbomb.network.events.forward

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.network.dispatch.HttpMessageDispatcher
import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.messages.DespawnedEntityHttpMessage

class DespawnEntityEventForwarder : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as EntityNetwork
        HttpMessageDispatcher.instance.dispatch(DespawnedEntityHttpMessage(info), private = false)
    }
}