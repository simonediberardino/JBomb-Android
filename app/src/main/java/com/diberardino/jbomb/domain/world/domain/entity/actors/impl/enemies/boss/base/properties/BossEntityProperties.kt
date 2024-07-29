package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.properties

import com.diberardino.jbomb.audio.SoundModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties.CharacterEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.MovingEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.Boss
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.values.DrawPriority
import com.diberardino.jbomb.domain.world.types.EntityTypes
import java.util.TreeMap

open class BossEntityProperties(
    drawPriority: DrawPriority = Boss.DEFAULT.DRAW_PRIORITY,
    types: EntityTypes,
    supportedDirections: List<Direction> = MovingEntity.DEFAULT.SUPPORTED_DIRECTIONS,
    stepSound: SoundModel? = Character.DEFAULT.STEP_SOUND,
    imageDirections: List<Direction> = Character.DEFAULT.IMAGE_DIRECTIONS,
    deathSound: SoundModel = Boss.DEFAULT.DEATH_SOUND,
    val healthStatusMap: TreeMap<Int, Int>? = Boss.DEFAULT.HEALTH_STATUS_MAP
): CharacterEntityProperties(
        drawPriority = drawPriority,
        types = types,
        supportedDirections = supportedDirections,
        stepSound = stepSound,
        imageDirections = imageDirections,
        deathSound = deathSound
)