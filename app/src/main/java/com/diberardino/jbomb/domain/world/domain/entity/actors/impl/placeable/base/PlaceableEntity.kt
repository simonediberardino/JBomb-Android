package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.base

import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.movable_block.MovableBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.base.state.PlaceableEntityState
import com.diberardino.jbomb.mappers.dtoToEntityNetwork
import com.diberardino.jbomb.network.entity.PlaceableEntityNetwork

abstract class PlaceableEntity(
        coordinates: Coordinates? = null
) : MovableBlock(coordinates) {

    constructor() : this(null)

    constructor(id: Long) : this(null) {
        this.info.id = id
    }

    abstract override val state: PlaceableEntityState

    override fun toEntityNetwork(): PlaceableEntityNetwork {
        return this.dtoToEntityNetwork()
    }
}