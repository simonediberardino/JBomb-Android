package com.diberardino.jbomb.network.events.process

import android.util.Log
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.firing_enemy.FiringEnemy
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.utility.Extensions.getOrTrim

class FireEventProcessor: HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as Map<String, String>
        val firingEntityid = info.getOrTrim("id")?.toLong() ?: return
        val direction = Direction.values()[info.getOrTrim("direction")?.toInt() ?: return]

        Log.i(this.javaClass.simpleName, "FireEventProcessor received $firingEntityid")

        val firingEntity = JBomb.match.getEntityById(firingEntityid) ?: return
        if (firingEntity is FiringEnemy) {
            firingEntity.logic.fire(direction)
        }
    }
}