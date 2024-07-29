package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.ghost.state

import com.diberardino.jbomb.audio.SoundModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.MovingEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.Boss
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.properties.BossEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.ghost.GhostBoss
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.values.DrawPriority
import java.util.*

class GhostBossProperties(
    drawPriority: DrawPriority = Boss.DEFAULT.DRAW_PRIORITY,
    types: EntityTypes,
    supportedDirections: List<Direction> = MovingEntity.DEFAULT.SUPPORTED_DIRECTIONS,
    stepSound: SoundModel? = Character.DEFAULT.STEP_SOUND,
    imageDirections: List<Direction> = GhostBoss.DEFAULT.IMAGE_DIRECTIONS,
    deathSound: SoundModel = Boss.DEFAULT.DEATH_SOUND,
    healthStatusMap: TreeMap<Int, Int>? = GhostBoss.DEFAULT.HEALTH_STATUS_MAP
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