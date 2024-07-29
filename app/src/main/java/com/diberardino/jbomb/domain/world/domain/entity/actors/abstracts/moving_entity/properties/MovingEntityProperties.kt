package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.properties

import com.diberardino.jbomb.audio.SoundModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.values.DrawPriority
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.MovingEntity
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction

open class MovingEntityProperties(
    drawPriority: DrawPriority,
    types: EntityTypes,
    val supportedDirections: List<Direction> = MovingEntity.DEFAULT.SUPPORTED_DIRECTIONS,
    val stepSound: SoundModel? = MovingEntity.DEFAULT.STEP_SOUND
) : EntityProperties(drawPriority, types)