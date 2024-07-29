package com.diberardino.jbomb.network.events.process

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.mappers.toEntity
import com.diberardino.jbomb.network.gamehandler.ClientGameHandler
import com.diberardino.jbomb.utils.dev.Extensions.getOrTrim
import com.diberardino.jbomb.utils.dev.Log

class SpawnedEntityHttpEventProcessor : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as Map<String, String>

        val entityId = info.getOrTrim("entityId")?.toLong() ?: return
        val entityType = info.getOrTrim("entityType")?.toInt() ?: return
        val locationString = info.getOrTrim("location") ?: return
        val locTokens = locationString.split(" ").map { it.toInt() }
        val location = Coordinates(locTokens[0], locTokens[1]).fromAbsolute()

        Log.i(this.javaClass.simpleName, "SpawnedEntityHttpEventProcessor received $info")
        Log.i(this.javaClass.simpleName, "Type $entityType $entityId")

        val entity = createEntity(entityId, entityType, info) ?: return
        entity.info.position = location
        entity.updateInfo(info)
        entity.logic.spawn(forceSpawn = true)
    }

    private fun createEntity(
            entityId: Long,
            entityType: Int,
            extra: Map<String, String>? = null
    ): Entity? {
        return if (entityId == (JBomb.match.onlineGameHandler as ClientGameHandler?)?.id) {
            EntityTypes.Player.toEntity(entityId, extra)
        } else {
            EntityTypes.values().getOrElse(entityType) { return null }.toEntity(entityId)
        }
    }

}