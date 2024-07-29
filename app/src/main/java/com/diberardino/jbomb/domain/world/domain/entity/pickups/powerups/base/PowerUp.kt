package com.diberardino.jbomb.domain.world.domain.pickups.powerups.base

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityImageModel
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable.EntityInteractable
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.Player
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.LivesPowerUp
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.base.logic.PowerUpLogic
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.state.PowerUpState
import com.diberardino.jbomb.values.Dimensions.DEFAULT_SIZE

/**
 * The abstract PowerUp class is a superclass for all power-ups in the game.
 */
abstract class PowerUp : EntityInteractable {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val state: PowerUpState = PowerUpState(entity = this)
    
    override val image: EntityImageModel = EntityImageModel(entity = this)
    abstract override val logic: PowerUpLogic

    companion object {
        // A static array of power-up classes
        val POWER_UPS: Array<Class<out PowerUp>> = arrayOf(
                LivesPowerUp::class.java,

        )
    }

    abstract val tag: String?

    internal object DEFAULT {
        // The default duration for a power-up, in seconds
        const val DURATION_SEC: Int = 15
        val SIZE = DEFAULT_SIZE
        const val IS_DISPLAYABLE = true
        val INTERACTION_ENTITIES: MutableSet<Class<out Entity>> = hashSetOf(Player::class.java)
        val OBSTACLES: MutableSet<Class<out Entity>> = hashSetOf(Player::class.java)
        val INCOMPATIBLE_POWER_UPS = mutableListOf<Class<out PowerUp>>()
    }
}