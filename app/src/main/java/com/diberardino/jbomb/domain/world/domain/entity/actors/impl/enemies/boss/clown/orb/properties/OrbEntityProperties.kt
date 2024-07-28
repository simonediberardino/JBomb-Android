package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.orb.properties

import com.diberardino.jbomb.values.DrawPriority
import game.audio.SoundModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties.CharacterEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.MovingEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.orb.Orb
import com.diberardino.jbomb.domain.world.domain.geo.Direction
import game.domain.world.types.EntityTypes
import game.values.DrawPriority

open class OrbEntityProperties(
    types: EntityTypes,
    drawPriority: DrawPriority = Orb.DEFAULT.DRAW_PRIORITY,
    supportedDirections: List<Direction> = MovingEntity.DEFAULT.SUPPORTED_DIRECTIONS,
    stepSound: SoundModel? = Character.DEFAULT.STEP_SOUND,
    imageDirections: List<Direction> = Character.DEFAULT.IMAGE_DIRECTIONS,
    deathSound: SoundModel = Character.DEFAULT.DEATH_SOUND
) : CharacterEntityProperties(
        drawPriority = drawPriority,
        types = types,
        supportedDirections = supportedDirections,
        stepSound = stepSound,
        imageDirections = imageDirections,
        deathSound = deathSound
)