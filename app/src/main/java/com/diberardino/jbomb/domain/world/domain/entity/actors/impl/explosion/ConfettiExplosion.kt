package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.graphics.ExplosionImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.state.ExplosionProperties
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.utility.Paths.enemiesFolder
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.AbstractExplosion
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.Explosive
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.domain.world.types.EntityTypes

class ConfettiExplosion : AbstractExplosion {
    constructor(
        owner: Entity,
        coordinates: Coordinates,
        direction: Direction,
        explosive: Explosive) : super(owner, coordinates, direction, explosive)

    constructor(
        owner: Entity,
        coordinates: Coordinates,
        direction: Direction,
        distanceFromBomb: Int,
        explosive: Explosive) : super(owner, coordinates, direction, distanceFromBomb, explosive)

    constructor(
        owner: Entity,
        coordinates: Coordinates,
        direction: Direction,
        distanceFromExplosive: Int,
        explosive: Explosive,
        canExpand: Boolean) : super(owner, coordinates, direction, distanceFromExplosive, explosive, canExpand)

    override val properties: ExplosionProperties = ExplosionProperties(
            types = EntityTypes.ConfettiExplosion,
            explosionClass = javaClass
    )

    override val image: ExplosionImageModel = ExplosionImageModel(
            entity = this,
            entitiesAssetsPath = "${enemiesFolder}/clown/clown_explosion/clown_explosion"
    )
}
