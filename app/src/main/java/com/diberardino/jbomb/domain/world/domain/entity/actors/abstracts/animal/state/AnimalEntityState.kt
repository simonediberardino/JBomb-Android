package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.animal.state

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.animal.AnimalEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties.CharacterEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable.EntityInteractable
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.MovingEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.State
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction

open class AnimalEntityState(
    entity: Entity,
    isSpawned: Boolean = Entity.DEFAULT.SPAWNED,
    isImmune: Boolean = Entity.DEFAULT.IMMUNE,
    state: State? = Entity.DEFAULT.STATE,
    isInvisible: Boolean = Entity.DEFAULT.IS_INVISIBLE,
    size: Int = Character.DEFAULT.SIZE,
    alpha: Float = Entity.DEFAULT.ALPHA,
    interactionEntities: MutableSet<Class<out Entity>> = AnimalEntity.DEFAULT.INTERACTION_ENTITIES,
    whitelistObstacles: MutableSet<Class<out Entity>> = EntityInteractable.DEFAULT.WHITELIST_OBSTACLES,
    obstacles: Set<Class<out Entity>> = AnimalEntity.DEFAULT.OBSTACLES,
    lastInteractionTime: Long = EntityInteractable.DEFAULT.LAST_INTERACTION_TIME,
    lastDamageTime: Long = EntityInteractable.DEFAULT.LAST_DAMAGE_TIME,
    attackDamage: Int = EntityInteractable.DEFAULT.ATTACK_DAMAGE,
    direction: Direction = MovingEntity.DEFAULT.DIRECTION,
    lastDirectionUpdate: Long = Character.DEFAULT.LAST_DIRECTION_UPDATE,
    previousDirection: Direction? = Character.DEFAULT.PREVIOUS_DIRECTION,
    canMove: Boolean = Character.DEFAULT.CAN_MOVE,
    maxHp: Int = Character.DEFAULT.MAX_HP,
    speed: Float = Character.DEFAULT.SPEED
) : CharacterEntityState(
        entity = entity,
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
        attackDamage = attackDamage,
        direction = direction,
        lastDirectionUpdate = lastDirectionUpdate,
        previousDirection = previousDirection,
        canMove = canMove,
        maxHp = maxHp,
        speed = speed
) {
    open val freezeOnCollideWithPlayer: Boolean = true
}