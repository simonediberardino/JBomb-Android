package com.diberardino.jbomb.network.events.forward

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.network.dispatch.HttpMessageDispatcher
import com.diberardino.jbomb.network.messages.UpdateEnemiesCountMessage

class UpdateEnemiesCountEventForwarder : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val count = extras[0] as Int
        HttpMessageDispatcher.instance.dispatch(UpdateEnemiesCountMessage(count))
    }
}