package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.properties

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.properties.BlockEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.AbstractExplosion
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp

class DestroyableBlockState(
    entity: Entity,
    var powerUpClass: Class<out PowerUp>? = null
) : BlockEntityState(
        entity = entity,
        interactionEntities = mutableSetOf(AbstractExplosion::class.java)
)