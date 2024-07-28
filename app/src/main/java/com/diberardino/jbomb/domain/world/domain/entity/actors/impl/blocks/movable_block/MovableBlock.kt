package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.movable_block

import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.Block
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates

abstract class MovableBlock : Block {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)
}
