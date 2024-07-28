package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity

import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable.EntityInteractable
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.logic.IMovingEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.properties.MovingEntityProperties
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.geo.Direction

abstract class MovingEntity : EntityInteractable {
    constructor(coordinates: Coordinates?) : super(coordinates)
    constructor(id: Long) : super(id)
    constructor() : super()

    abstract override val logic: IMovingEntityLogic
    abstract override val properties: MovingEntityProperties

    internal object DEFAULT {
        val SUPPORTED_DIRECTIONS: List<Direction>
            get() = Direction.values().asList()
        val STEP_SOUND = null
        val DIRECTION: Direction
            get() = Direction.DOWN
    }
}
