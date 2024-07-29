package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.properties

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.properties.EnemyEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable.EntityInteractable
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.MovingEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.Boss
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.State
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction

open class BossEntityState(
    entity: Entity,
    size: Int = Boss.DEFAULT.SIZE,
    interactionEntities: MutableSet<Class<out Entity>> = Enemy.DEFAULT.INTERACTION_ENTITIES,
    speed: Float = Character.DEFAULT.SPEED,
    maxHp: Int = Character.DEFAULT.MAX_HP,
    var currRageStatus: Int = Boss.DEFAULT.START_RAGE_STATUS,
    isSpawned: Boolean = Entity.DEFAULT.SPAWNED,
    isImmune: Boolean = Entity.DEFAULT.IMMUNE,
    state: State? = Entity.DEFAULT.STATE,
    isInvisible: Boolean = Entity.DEFAULT.IS_INVISIBLE,
    alpha: Float = Entity.DEFAULT.ALPHA,
    whitelistObstacles: MutableSet<Class<out Entity>> = EntityInteractable.DEFAULT.WHITELIST_OBSTACLES,
    obstacles: Set<Class<out Entity>> = Boss.DEFAULT.OBSTACLES,
    lastInteractionTime: Long = EntityInteractable.DEFAULT.LAST_INTERACTION_TIME,
    lastDamageTime: Long = EntityInteractable.DEFAULT.LAST_DAMAGE_TIME,
    attackDamage: Int = EntityInteractable.DEFAULT.ATTACK_DAMAGE,
    direction: Direction = MovingEntity.DEFAULT.DIRECTION,
    lastDirectionUpdate: Long = Character.DEFAULT.LAST_DIRECTION_UPDATE,

    previousDirection: Direction? = Character.DEFAULT.PREVIOUS_DIRECTION,
    canMove: Boolean = Character.DEFAULT.CAN_MOVE
) : EnemyEntityState(
        entity = entity,
        size = size,
        interactionEntities = interactionEntities,
        speed = speed,
        maxHp = maxHp,
        isSpawned = isSpawned,
        isImmune = isImmune,
        state = state,
        isInvisible = isInvisible,
        alpha = alpha,
        whitelistObstacles = whitelistObstacles,
        obstacles = obstacles,
        lastInteractionTime = lastInteractionTime,
        lastDamageTime = lastDamageTime,
        attackDamage = attackDamage,
        direction = direction,
        lastDirectionUpdate = lastDirectionUpdate,

        previousDirection = previousDirection,
        canMove = canMove
) {
    override val followPlayers: Boolean = false
}