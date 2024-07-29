package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable.logic.IEntityInteractableLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable.state.EntityInteractableState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.DestroyableBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.hard_block.HardBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb

abstract class EntityInteractable : Entity {
    abstract override val logic: IEntityInteractableLogic
    abstract override val state: EntityInteractableState

    /**
     * Constructs an interactive entity with the given coordinates.
     *
     * @param coordinates the coordinates of the entity
     */
    constructor(coordinates: Coordinates?) : super(coordinates)
    constructor(id: Long) : super(id)
    constructor() : super()

    companion object {
        const val SHOW_DEATH_PAGE_DELAY_MS: Long = 2500
        const val INTERACTION_DELAY_MS: Long = 500
    }

    internal object DEFAULT {
        val WHITELIST_OBSTACLES: MutableSet<Class<out Entity>>
            get() = hashSetOf()
        val COLLIDED_ENTITIES: MutableSet<Entity>
            get() = hashSetOf()
        val OBSTACLES: MutableSet<Class<out Entity>>
            get() = mutableSetOf(Bomb::class.java, Character::class.java, HardBlock::class.java, DestroyableBlock::class.java)
        val LAST_INTERACTION_TIME: Long = 0L
        val LAST_DAMAGE_TIME: Long = 0L
        val ATTACK_DAMAGE = 100
    }
}
