package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties

import com.diberardino.jbomb.audio.SoundModel
import com.diberardino.jbomb.values.DrawPriority
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.MovingEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.properties.MovingEntityProperties
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction

open class CharacterEntityProperties(
    drawPriority: DrawPriority = Character.DEFAULT.DRAW_PRIORITY,
    types: EntityTypes,
    supportedDirections: List<Direction> = MovingEntity.DEFAULT.SUPPORTED_DIRECTIONS,
    stepSound: SoundModel? = Character.DEFAULT.STEP_SOUND,
    val imageDirections: List<Direction> = Character.DEFAULT.IMAGE_DIRECTIONS,
    val deathSound: SoundModel = Character.DEFAULT.DEATH_SOUND
) : MovingEntityProperties(
        drawPriority,
        types,
        supportedDirections,
        stepSound
) {
    open var name: String? = null
}