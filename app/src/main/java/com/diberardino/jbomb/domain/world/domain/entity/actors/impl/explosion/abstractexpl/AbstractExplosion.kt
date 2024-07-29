package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.graphics.ExplosionGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.graphics.ExplosionImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.logic.ExplosionLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.logic.IExplosionLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.state.ExplosionProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.state.ExplosionState
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.values.Dimensions.DEFAULT_SIZE
import com.diberardino.jbomb.values.Dimensions.GRID_SIZE
import com.diberardino.jbomb.values.DrawPriority
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.MovingEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.Explosive
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction

/**
 * An abstract class for in-game explosions;
 */
abstract class AbstractExplosion(owner: Entity,
                                 coordinates: Coordinates?,
                                 direction: Direction,
                                 distanceFromExplosive: Int,
                                 explosive: Explosive,
                                 canExpand: Boolean
) : MovingEntity(coordinates) {
    constructor(owner: Entity,
                coordinates: Coordinates?,
                direction: Direction,
                explosive: Explosive) : this(owner, coordinates, direction, 0, explosive)

    constructor(owner: Entity,
                coordinates: Coordinates?,
                direction: Direction,
                distanceFromBomb: Int,
                explosive: Explosive) : this(owner, coordinates, direction, distanceFromBomb, explosive, true)

    abstract override val properties: ExplosionProperties
    abstract override val image: ExplosionImageModel
    override val logic: IExplosionLogic = ExplosionLogic(this)
    
    override val graphicsBehavior: IEntityGraphicsBehavior = ExplosionGraphicsBehavior()

    override val state: ExplosionState = ExplosionState(
            entity = this,
            owner = owner,
            explosive = explosive
    )

    init {
        state.distanceFromExplosive = distanceFromExplosive
        state.explosive = explosive
        state.canExpand = canExpand
        state.owner = owner
        state.direction = direction
    }

    companion object {
        val SIZE = DEFAULT_SIZE
        val SPAWN_OFFSET = (GRID_SIZE - SIZE) / 2
        var MAX_EXPLOSION_LENGTH = 5
        const val BOMB_STATES = 3
    }

    internal object DEFAULT {
        const val IGNORE_CENTER = true
        val DRAW_PRIORITY = DrawPriority.DRAW_PRIORITY_3
        const val DISTANCE_FROM_EXPLOSIVE = 0
        const val CAN_EXPAND = false
        const val IMAGE_REFRESH_RATE = 100
        const val CENTER_VISIBLE = true
    }
}