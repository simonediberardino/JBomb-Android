package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.flying_enemy.properties

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.flying_enemy.FlyingEnemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.properties.EnemyEntityState

class FlyingEnemyState(
    entity: Entity,
    whitelistObstacles: MutableSet<Class<out Entity>> = FlyingEnemy.DEFAULT.WHITELIST_OBSTACLES
) : EnemyEntityState(
        entity = entity,
        whitelistObstacles = whitelistObstacles,
)