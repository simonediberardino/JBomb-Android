package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.properties

import com.diberardino.jbomb.values.DrawPriority
import game.audio.SoundModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties.CharacterEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.MovingEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.Player
import com.diberardino.jbomb.domain.world.domain.geo.Direction
import game.domain.world.types.EntityTypes
import game.values.DrawPriority

open class BomberEntityProperties(
    drawPriority: DrawPriority = Character.DEFAULT.DRAW_PRIORITY,
    types: EntityTypes,
    supportedDirections: List<Direction> = MovingEntity.DEFAULT.SUPPORTED_DIRECTIONS,
    stepSound: SoundModel? = BomberEntity.DEFAULT.STEP_SOUND,
    imageDirections: List<Direction> = Character.DEFAULT.IMAGE_DIRECTIONS,
    deathSound: SoundModel = Player.DEFAULT.DEATH_SOUND,
    var skinId: Int
): CharacterEntityProperties(
        drawPriority = drawPriority,
        types = types,
        supportedDirections = supportedDirections,
        stepSound = stepSound,
        imageDirections = imageDirections,
        deathSound = deathSound
)
