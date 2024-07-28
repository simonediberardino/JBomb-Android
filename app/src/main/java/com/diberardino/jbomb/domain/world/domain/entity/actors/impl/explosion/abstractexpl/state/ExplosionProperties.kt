package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.state

import com.diberardino.jbomb.audio.SoundModel
import com.diberardino.jbomb.values.DrawPriority
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.MovingEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.properties.MovingEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.AbstractExplosion
import com.diberardino.jbomb.domain.world.domain.geo.Direction
import game.domain.world.types.EntityTypes

class ExplosionProperties(
        drawPriority: DrawPriority = AbstractExplosion.DEFAULT.DRAW_PRIORITY,
        types: EntityTypes,
        supportedDirections: List<Direction> = MovingEntity.DEFAULT.SUPPORTED_DIRECTIONS,
        stepSound: SoundModel? = null,
        val explosionClass: Class<out AbstractExplosion>,
        val ignoreCenter: Boolean = AbstractExplosion.DEFAULT.IGNORE_CENTER
): MovingEntityProperties(
        drawPriority = drawPriority,
        types = types,
        supportedDirections = supportedDirections,
        stepSound = stepSound
)