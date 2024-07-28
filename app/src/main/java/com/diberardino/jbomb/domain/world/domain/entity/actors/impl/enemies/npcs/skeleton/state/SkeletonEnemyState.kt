package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.skeleton.state

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.firing_enemy.state.FiringEnemyState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.skeleton.SkeletonEnemy

open class SkeletonEnemyState(
    entity: Entity,
    speed: Float = SkeletonEnemy.DEFAULT.SPEED
) : FiringEnemyState(
        entity = entity,
        speed = speed
)