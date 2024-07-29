package com.diberardino.jbomb.network.entity

import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates


class BomberEntityNetwork(
        entityId: Long,
        entityLocation: Coordinates,
        entityType: Int,
        direction: Int,
        isImmune: Boolean,
        name: String? = null,
        val currExplosionLength: Int,
        val currentBombs: Int,
        val skinId: Int,
        val hp: Int
) : CharacterNetwork(
        entityId = entityId,
        entityLocation = entityLocation,
        entityType = entityType,
        isImmune = isImmune,
        direction = direction,
        name = name
)