package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.logic

import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.logic.IBlockEntityLogic

interface IBombLogic: IBlockEntityLogic {
    fun explode()
    fun trigger()
}