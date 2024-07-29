package com.diberardino.jbomb.network.entity

import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates

open class EntityNetwork(
    val entityId: Long,
    val entityLocation: Coordinates?,
    val entityType: Int,
    val isImmune: Boolean
)