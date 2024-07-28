package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.audio.SoundModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.graphics.BossEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.logic.BossEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.properties.BossEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.properties.BossEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ai_enemy.AiEnemy
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.values.Dimensions.DEFAULT_SIZE
import com.diberardino.jbomb.values.DrawPriority
import java.util.TreeMap

/**
 * An abstract class for enemy bosses;
 */
abstract class Boss : AiEnemy {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    constructor() : this(null) {}

    abstract override val logic: BossEntityLogic
    abstract override val state: BossEntityState
    abstract override val properties: BossEntityProperties
    abstract override val graphicsBehavior: BossEntityGraphicsBehavior

    internal object DEFAULT {
        val DRAW_PRIORITY = DrawPriority.DRAW_PRIORITY_4
        val ATTACK_DAMAGE = 1000
        val SIZE = DEFAULT_SIZE * 2
        val MAX_HP: Int by lazy {
            JBomb.match.currentLevel.info.bossMaxHealth
        }
        val OBSTACLES: Set<Class<out Entity>>
            get() = emptySet()
        val DEATH_SOUND = SoundModel.BOSS_DEATH
        val HEALTH_STATUS_MAP: TreeMap<Int, Int>
            get() = TreeMap<Int, Int>()
        val START_RAGE_STATUS = 0
    }
}
