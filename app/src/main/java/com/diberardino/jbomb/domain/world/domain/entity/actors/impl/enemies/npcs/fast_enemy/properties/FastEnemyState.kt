package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.fast_enemy.properties

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.properties.EnemyEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.fast_enemy.FastPurpleBall

class FastEnemyState(
    entity: Entity,
    size: Int = FastPurpleBall.DEFAULT.SIZE,
    speed: Float = FastPurpleBall.DEFAULT.SPEED
) : EnemyEntityState(
        entity = entity,
        size = size,
        speed = speed
)