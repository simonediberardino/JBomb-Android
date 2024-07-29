package com.diberardino.jbomb.network.events.forward

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.network.dispatch.HttpMessageDispatcher
import com.diberardino.jbomb.network.messages.LevelInfoHttpMessage
import com.diberardino.jbomb.utility.Extensions.getOrTrim

class LevelInfoHttpEventForwarder : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as Map<String, String>
        val clientId = info.getOrTrim("id")?.toLong() ?: return
        val levelId = info.getOrTrim("levelId")?.toInt() ?: return
        val worldId = info.getOrTrim("worldId")?.toInt() ?: return

        HttpMessageDispatcher.instance.dispatch(LevelInfoHttpMessage(clientId, levelId, worldId), clientId)
    }
}