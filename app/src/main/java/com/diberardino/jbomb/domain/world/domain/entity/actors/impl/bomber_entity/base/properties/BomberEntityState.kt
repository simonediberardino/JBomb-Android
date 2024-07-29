package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.properties

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties.CharacterEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable.EntityInteractable
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.MovingEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.State
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.domain.world.domain.items.UsableItem
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp

open class BomberEntityState(
    entity: BomberEntity,
    isSpawned: Boolean = Entity.DEFAULT.SPAWNED,
    isImmune: Boolean = Entity.DEFAULT.IMMUNE,
    state: State? = Entity.DEFAULT.STATE,
    isInvisible: Boolean = Entity.DEFAULT.IS_INVISIBLE,
    size: Int = Character.DEFAULT.SIZE,
    alpha: Float = Entity.DEFAULT.ALPHA,
    interactionEntities: MutableSet<Class<out Entity>> = BomberEntity.DEFAULT.INTERACTION_ENTITIES,
    whitelistObstacles: MutableSet<Class<out Entity>> = EntityInteractable.DEFAULT.WHITELIST_OBSTACLES,
    obstacles: Set<Class<out Entity>> = EntityInteractable.DEFAULT.OBSTACLES,
    lastInteractionTime: Long = EntityInteractable.DEFAULT.LAST_INTERACTION_TIME,
    lastDamageTime: Long = EntityInteractable.DEFAULT.LAST_DAMAGE_TIME,
    attackDamage: Int = EntityInteractable.DEFAULT.ATTACK_DAMAGE,
    direction: Direction = MovingEntity.DEFAULT.DIRECTION,
    lastDirectionUpdate: Long = Character.DEFAULT.LAST_DIRECTION_UPDATE,

    previousDirection: Direction? = Character.DEFAULT.PREVIOUS_DIRECTION,
    canMove: Boolean = Character.DEFAULT.CAN_MOVE,
    maxHp: Int = BomberEntity.DEFAULT.MAX_HP,
    speed: Float = Character.DEFAULT.SPEED
) : CharacterEntityState(
        entity = entity,
        interactionEntities = interactionEntities,
        size = size,
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
        canMove = canMove,
        speed = speed,
        maxHp = maxHp
) {
    override val canRespawn: Boolean
        get() = true

    lateinit var weapon: UsableItem
    val entitiesClassesMouseClick: MutableSet<Class<out Entity>> = BomberEntity.DEFAULT.MOUSE_CLICK_INTERACTION_ENTITIES
    val entitiesClassesMouseDrag: MutableSet<Class<out Entity>> = BomberEntity.DEFAULT.MOUSE_DRAG_INTERACTION_ENTITIES
    var currExplosionLength = 0
    var placedBombs = 0
    var lastPlacedBombTime: Long = 0
    var currentBombs = 0
    var maxBombs: Int = 0

    var bombsSolid: Boolean = true
        set(value) {
            field = value

            if (!value) {
                whitelistObstacles.add(Bomb::class.java)
            } else if (!forceBombsSolid) {
                whitelistObstacles.remove(Bomb::class.java)
            }
        }

    var forceBombsSolid: Boolean = false
    var activePowerUps: MutableList<Class<out PowerUp>> = mutableListOf()
    var temporaryActivePowerUps: MutableSet<Class<out PowerUp>> = mutableSetOf()
}
