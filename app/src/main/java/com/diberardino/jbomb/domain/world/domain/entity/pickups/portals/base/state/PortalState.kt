package com.diberardino.jbomb.domain.world.domain.pickups.portals.base.state

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable.EntityInteractable
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.State
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.state.PowerUpState

open class PortalState(
    entity: Entity,
    isSpawned: Boolean = Entity.DEFAULT.SPAWNED,
    isImmune: Boolean = Entity.DEFAULT.IMMUNE,
    state: State? = Entity.DEFAULT.STATE,
    isInvisible: Boolean = Entity.DEFAULT.IS_INVISIBLE,
    size: Int = PowerUp.DEFAULT.SIZE,
    alpha: Float = Entity.DEFAULT.ALPHA,
    interactionEntities: MutableSet<Class<out Entity>> = PowerUp.DEFAULT.INTERACTION_ENTITIES,
    whitelistObstacles: MutableSet<Class<out Entity>> = EntityInteractable.DEFAULT.WHITELIST_OBSTACLES,
    obstacles: Set<Class<out Entity>> = PowerUp.DEFAULT.OBSTACLES,
    lastInteractionTime: Long = EntityInteractable.DEFAULT.LAST_INTERACTION_TIME,
    lastDamageTime: Long = EntityInteractable.DEFAULT.LAST_DAMAGE_TIME,
    attackDamage: Int = EntityInteractable.DEFAULT.ATTACK_DAMAGE
) : PowerUpState(entity = entity,
        isSpawned = isSpawned,
        isImmune = isImmune,
        state = state,
        isInvisible = isInvisible,
        size = size,
        alpha = alpha,
        interactionEntities = interactionEntities,
        whitelistObstacles = whitelistObstacles,
        obstacles = obstacles,
        lastInteractionTime = lastInteractionTime,
        lastDamageTime = lastDamageTime,
        attackDamage = attackDamage) {
    override val isDisplayable: Boolean = false
    override val duration: Int = 0
}