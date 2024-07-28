package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.flying_enemy

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ai_enemy.AiEnemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.flying_enemy.properties.FlyingEnemyState
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.properties.EnemyEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.DestroyableBlock

/**
 * Enemy that can fly over destroyable blocks;
 */
abstract class FlyingEnemy : AiEnemy {
    constructor() : super()
    constructor(coordinates: Coordinates?) : super(coordinates)
    constructor(id: Long) : super(id)

    override val state: EnemyEntityState = FlyingEnemyState(entity = this)

    internal object DEFAULT {
        val WHITELIST_OBSTACLES: MutableSet<Class<out Entity>>
            get() = mutableSetOf(DestroyableBlock::class.java)
    }
}