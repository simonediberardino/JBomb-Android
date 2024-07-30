package com.diberardino.jbomb.network.events.process

import android.util.Log
import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.items.ItemsTypes
import com.diberardino.jbomb.utility.Extensions.getOrTrim


class UseItemHttpEventProcessor : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as Map<String, String>

        Log.e(this.javaClass.simpleName, "UseItemHttpEventProcessor info $info")
        val entityId = info.getOrTrim("entityId")?.toLong() ?: return
        val itemType = info.getOrTrim("itemType")?.toInt() ?: return
        val itemId = info.getOrTrim("itemId")?.toLong()

        val entity: Entity = JBomb.match.getEntityById(entityId) ?: return
        val item = ItemsTypes.values()[itemType].toItem()
        JBomb.match.give(entity as BomberEntity, item)

        item.use(itemId)
    }
}