package com.diberardino.jbomb.network.events.process

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb
import com.diberardino.jbomb.utils.dev.Extensions.getOrTrim
import com.diberardino.jbomb.utils.dev.Log

class BombExplodedEventProcessor: HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as Map<String, String>
        val callerId = info.getOrTrim("entityId")?.toLong() ?: return
        val bombId = info.getOrTrim("bombId")?.toLong() ?: return

        Log.e(this.javaClass.simpleName, "BombExplodedEventProcessor received $info")

        Log.e(this.javaClass.simpleName, "BombExplodedEventProcessor alive entities ${JBomb.match.bombs}")

        val entity = JBomb.match.getEntityById(bombId) as Bomb?
        entity?.logic?.explode()
    }
}