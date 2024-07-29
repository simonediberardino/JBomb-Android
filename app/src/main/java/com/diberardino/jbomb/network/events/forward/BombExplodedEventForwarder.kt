package com.diberardino.jbomb.network.events.forward

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.network.dispatch.HttpMessageDispatcher
import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.messages.BombExplodedHttpMessage
import com.diberardino.jbomb.network.messages.CollideEventMessage

class BombExplodedEventForwarder: HttpEvent {
    override fun invoke(vararg extras: Any) {
        val victim = extras[0] as EntityNetwork
        val self = extras[1] as EntityNetwork
        HttpMessageDispatcher.instance.dispatch(BombExplodedHttpMessage(victim, self), private = false)
    }
}