package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.hard_block

import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.Block

abstract class HardBlock : Block {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)
}
