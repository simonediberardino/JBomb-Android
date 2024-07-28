package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.properties

import com.diberardino.jbomb.audio.SoundModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.Boss
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.properties.BossEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.Clown
import com.diberardino.jbomb.domain.world.domain.geo.Direction
import game.domain.world.types.EntityTypes
import game.values.DrawPriority
import java.util.TreeMap

class ClownProperties(
    drawPriority: DrawPriority = Boss.DEFAULT.DRAW_PRIORITY,
    types: EntityTypes,
    supportedDirections: List<Direction> = Clown.DEFAULT.SUPPORTED_DIRECTIONS,
    stepSound: SoundModel? = Character.DEFAULT.STEP_SOUND,
    imageDirections: List<Direction> = Character.DEFAULT.IMAGE_DIRECTIONS,
    deathSound: SoundModel = Boss.DEFAULT.DEATH_SOUND,
    healthStatusMap: TreeMap<Int, Int>? = Clown.DEFAULT.HEALTH_STATUS_MAP
) : BossEntityProperties(
        drawPriority = drawPriority,
        types = types,
        supportedDirections = supportedDirections,
        stepSound = stepSound,
        imageDirections = imageDirections,
        deathSound = deathSound,
        healthStatusMap = healthStatusMap
) {
}