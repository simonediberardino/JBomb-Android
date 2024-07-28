package game.network.entity

import com.diberardino.jbomb.domain.world.domain.geo.Coordinates

class PlaceableEntityNetwork(
        entityId: Long,
        entityLocation: Coordinates?,
        entityType: Int,
        isImmune: Boolean,
        val callerId: Long)
    : EntityNetwork(entityId, entityLocation, entityType, isImmune)