package com.diberardino.jbomb.network.events.forward

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.network.entity.CharacterNetwork
import com.diberardino.jbomb.network.dispatch.HttpMessageDispatcher
import com.diberardino.jbomb.network.messages.LocationHttpMessage

class LocationUpdatedHttpEventForwarder : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as CharacterNetwork
        HttpMessageDispatcher.instance.dispatch(LocationHttpMessage(info), info.entityId, ignore = true, private = false)
    }
}