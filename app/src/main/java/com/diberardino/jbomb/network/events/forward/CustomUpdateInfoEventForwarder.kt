package com.diberardino.jbomb.network.events.forward

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.network.dispatch.HttpMessageDispatcher
import com.diberardino.jbomb.network.messages.UpdateInfoHttpMessage

class CustomUpdateInfoEventForwarder: HttpEvent {
    override fun invoke(vararg extras: Any) {
        val entityId = extras[0] as Long
        val params = extras[1] as Map<String, String>

        HttpMessageDispatcher.instance.dispatch(UpdateInfoHttpMessage(
                entityId = entityId,
                params = params
        ), private = false)

    }
}