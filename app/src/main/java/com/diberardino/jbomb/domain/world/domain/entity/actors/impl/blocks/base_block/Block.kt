package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.logic.BlockEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.logic.IBlockEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.properties.BlockEntityState
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.values.Dimensions.GRID_SIZE

abstract class Block : Entity {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val logic: IBlockEntityLogic = BlockEntityLogic(entity = this)
    override val state: BlockEntityState = BlockEntityState(entity = this)
    
    override val image: EntityImageModel = EntityImageModel(entity = this)

    companion object {
        val SIZE = GRID_SIZE
    }
}