package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.state

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.State
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.base.state.PlaceableEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb

class BombState(
    entity: Entity,
    isSpawned: Boolean = Entity.DEFAULT.SPAWNED,
    isImmune: Boolean = Entity.DEFAULT.IMMUNE,
    state: State? = Entity.DEFAULT.STATE,
    isInvisible: Boolean = Entity.DEFAULT.IS_INVISIBLE,
    size: Int = Bomb.DEFAULT.SIZE,
    alpha: Float = Entity.DEFAULT.ALPHA,
    interactionEntities: MutableSet<Class<out Entity>> = Bomb.DEFAULT.INTERACTION_ENTITIES,
    lastImageUpdate: Long = Entity.DEFAULT.LAST_IMAGE_UPDATE,
    override var caller: Character
) : PlaceableEntityState(entity, isSpawned, isImmune, state, isInvisible, size, alpha, interactionEntities, lastImageUpdate) 