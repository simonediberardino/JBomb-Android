package com.diberardino.jbomb.network.events.process

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.utils.dev.Extensions.getOrTrim
import com.diberardino.jbomb.utils.dev.Log

class AttackEntityEventProcessor : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as Map<String, String>
        val victimId = info.getOrTrim("entityId")?.toLong() ?: return
        val damage = info.getOrTrim("damage")?.toInt() ?: return

        Log.e(this.javaClass.simpleName, "AttackEntityEventProcessor received $victimId, $damage")

        val entity: Entity = JBomb.match.getEntityById(victimId) ?: return
        entity.logic.damageAnimation()
    }
}