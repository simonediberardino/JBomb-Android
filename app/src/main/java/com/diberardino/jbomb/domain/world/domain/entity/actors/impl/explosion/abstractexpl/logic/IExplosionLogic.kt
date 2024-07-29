package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.logic

import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.logic.IMovingEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.AbstractExplosion
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction

interface IExplosionLogic : IMovingEntityLogic {
    fun explode(): AbstractExplosion
    fun canExpand() : Boolean
    fun expandBomb(d: Direction, stepSize: Int)
    fun onObstacle(coordinates: Coordinates)
}