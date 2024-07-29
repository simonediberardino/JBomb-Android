package com.diberardino.jbomb.network.events.forward

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.network.dispatch.HttpMessageDispatcher
import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.messages.UpdateInfoHttpMessage

class UpdateInfoEventForwarder : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val entityNetwork = extras[0] as EntityNetwork
        HttpMessageDispatcher.instance.dispatch(UpdateInfoHttpMessage(entityNetwork), private = false)
    }
}