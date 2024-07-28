package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.fast_enemy.properties

import com.diberardino.jbomb.audio.SoundModel
import com.diberardino.jbomb.values.DrawPriority
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties.CharacterEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.MovingEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.fast_enemy.FastPurpleBall
import com.diberardino.jbomb.domain.world.domain.geo.Direction
import game.domain.world.types.EntityTypes

class FastEnemyProperties(
        drawPriority: DrawPriority = Character.DEFAULT.DRAW_PRIORITY,
        types: EntityTypes,
        supportedDirections: List<Direction> = MovingEntity.DEFAULT.SUPPORTED_DIRECTIONS,
        stepSound: SoundModel? = Character.DEFAULT.STEP_SOUND,
        imageDirections: List<Direction> = FastPurpleBall.DEFAULT.IMAGE_DIRECTIONS,
        deathSound: SoundModel = Character.DEFAULT.DEATH_SOUND
) : CharacterEntityProperties(
        drawPriority = drawPriority,
        types = types,
        supportedDirections = supportedDirections,
        stepSound = stepSound,
        imageDirections = imageDirections,
        deathSound = deathSound
) {
}