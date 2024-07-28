package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.orb.properties

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.properties.EnemyEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.orb.Orb
import com.diberardino.jbomb.domain.world.domain.geo.EnhancedDirection

open class OrbEntityState(
    entity: Entity,
    interactionEntities: MutableSet<Class<out Entity>> = Orb.DEFAULT.INTERACTION_ENTITIES,
    speed: Float = Orb.DEFAULT.SPEED,
    size: Int = Orb.DEFAULT.SIZE,
    maxHp: Int = Character.DEFAULT.MAX_HP,
    obstacles: MutableSet<Class<out Entity>> = Orb.DEFAULT.OBSTACLES
) : EnemyEntityState(
        entity = entity,
        size = size,
        speed = speed,
        interactionEntities = interactionEntities,
        maxHp = maxHp,
        obstacles = obstacles
) {
    /**
     * Only one field between enhancedDirection and direction can be instantiated at a time.
     * The enhancedDirection represents a direction that has been enhanced with additional directions.
     */
    var enhancedDirection: EnhancedDirection? = null
}