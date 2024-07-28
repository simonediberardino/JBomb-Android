package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.firing_enemy

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ai_enemy.AiEnemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.firing_enemy.logic.FiringEnemyLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.firing_enemy.state.FiringEnemyState
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.DestroyableBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.hard_block.HardBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.Explosive
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb

abstract class FiringEnemy : AiEnemy, Explosive {
    constructor() : super()
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val logic: FiringEnemyLogic = FiringEnemyLogic(entity = this)
    override val state: FiringEnemyState = FiringEnemyState(entity = this)
    override val maxExplosionDistance: Int = 4

    override val explosionObstacles: Set<Class<out Entity>>
        get() = setOf(
                HardBlock::class.java,
                DestroyableBlock::class.java
        )

    override val explosionInteractionEntities: Set<Class<out Entity>>
        get() = setOf(
                BomberEntity::class.java,
                Bomb::class.java
        )

    internal object DEFAULT {
        const val CAN_SHOOT = true
        const val LAST_FIRE = 0L
    }
}