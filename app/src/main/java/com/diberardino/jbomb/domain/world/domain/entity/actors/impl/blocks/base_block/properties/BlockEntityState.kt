package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.properties

import com.diberardino.jbomb.presentation.ui.panels.game.PitchPanel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.State

open class BlockEntityState(
    entity: Entity,
    isSpawned: Boolean = Entity.DEFAULT.SPAWNED,
    isImmune: Boolean = Entity.DEFAULT.IMMUNE,
    state: State? = Entity.DEFAULT.STATE,
    isInvisible: Boolean = Entity.DEFAULT.IS_INVISIBLE,
    size: Int = GRID_SIZE,
    alpha: Float = Entity.DEFAULT.ALPHA,
    interactionEntities: MutableSet<Class<out Entity>> = Entity.DEFAULT.INTERACTION_ENTITIES,
    lastImageUpdate: Long = Entity.DEFAULT.LAST_IMAGE_UPDATE
): EntityState(
        entity = entity,
        isSpawned = isSpawned,
        isImmune = isImmune,
        state = state,
        isInvisible = isInvisible,
        size = size,
        alpha = alpha,
        interactionEntities = interactionEntities,
        lastImageUpdate = lastImageUpdate
)