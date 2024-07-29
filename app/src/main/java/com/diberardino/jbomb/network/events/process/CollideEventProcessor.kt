package com.diberardino.jbomb.network.events.process

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.utility.Extensions.getOrTrim

class CollideEventProcessor: HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as Map<String, String>
        val victimId = info.getOrTrim("victimId")?.toLong() ?: return
        val selfId = info.getOrTrim("selfId")?.toLong() ?: return

        val victim = JBomb.match.getEntityById(victimId)
        val self = JBomb.match.getEntityById(selfId)

        victim?.logic?.interact(self)
    }
}