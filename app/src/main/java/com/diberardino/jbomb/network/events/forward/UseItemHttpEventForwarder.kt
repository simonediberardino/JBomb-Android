package com.diberardino.jbomb.network.events.forward

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.domain.world.domain.items.ItemsTypes
import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.dispatch.HttpMessageDispatcher
import com.diberardino.jbomb.network.messages.UseItemHttpMessage

class UseItemHttpEventForwarder : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val userDao = (extras[0] as EntityNetwork)
        val itemType = (extras[1] as ItemsTypes)
        val itemId = (extras[2] as Long)
        HttpMessageDispatcher.instance.dispatch(UseItemHttpMessage(userDao, itemType, itemId), private = false)
    }
}