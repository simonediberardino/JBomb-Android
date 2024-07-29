package com.diberardino.jbomb.network.events.process

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.utils.dev.Extensions.getOrTrim
import com.diberardino.jbomb.utils.dev.Log

class UpdateInfoEventProcessor : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as Map<String, String>
        Log.i(this.javaClass.simpleName, "UpdateInfoEventProcessor: $info")

        val clientId = info.getOrTrim("entityId")?.toLong() ?: return

        val entity = JBomb.match.getEntityById(clientId)
        entity?.updateInfo(info)
    }
}