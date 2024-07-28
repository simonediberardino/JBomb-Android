package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.logic

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityLogic

interface IBlockEntityLogic : IEntityLogic {
    fun destroy()
}