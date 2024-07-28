package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.movable_block.MovableBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.logic.IBlockEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.graphics.DestroyableBlockGraphics
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.logic.DestroyableBlockLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.properties.DestroyableBlockProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.properties.DestroyableBlockState
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp

class DestroyableBlock : MovableBlock {
    constructor(coordinates: Coordinates?, powerUpClass: Class<out PowerUp>? = null) : super(coordinates) {
        this.state.powerUpClass = powerUpClass
    }

    constructor(coordinates: Coordinates?) : this(coordinates, null)

    constructor(id: Long) : super(id)

    override val logic: IBlockEntityLogic = DestroyableBlockLogic(this)
    override val properties: DestroyableBlockProperties = DestroyableBlockProperties()
    override val image: EntityImageModel = EntityImageModel(entity = this)
    override val state: DestroyableBlockState = DestroyableBlockState(entity = this)
    
    override val graphicsBehavior: IEntityGraphicsBehavior = DestroyableBlockGraphics()
}
