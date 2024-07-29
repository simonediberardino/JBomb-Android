package com.diberardino.jbomb.network.events.process

import android.util.Log
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.utility.Extensions.getOrTrim

class DespawnedEntityHttpEventProcessor : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as Map<String, String>
        val entityId = info.getOrTrim("entityId")?.toLong() ?: return

        Log.i(this.javaClass.simpleName, "DespawnedEntityHttpEventProcessor received $entityId")

        val entity: Entity = JBomb.match.getEntityById(entityId) ?: return
        entity.logic.despawn()
    }
}