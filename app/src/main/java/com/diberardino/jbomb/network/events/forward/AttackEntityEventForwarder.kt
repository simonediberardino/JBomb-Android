package com.diberardino.jbomb.network.events.forward

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.dispatch.HttpMessageDispatcher
import com.diberardino.jbomb.network.messages.AttackEntityHttpMessage

class AttackEntityEventForwarder: HttpEvent {
    override fun invoke(vararg extras: Any) {
        val victim = extras[0] as EntityNetwork
        val damage = extras[1] as Int
        HttpMessageDispatcher.instance.dispatch(AttackEntityHttpMessage(victim, damage))
    }
}