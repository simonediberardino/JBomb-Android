package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.invisible_block

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.properties.BlockEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.hard_block.HardBlock
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import game.domain.world.types.EntityTypes
import java.awt.image.Bitmap

class InvisibleBlock : HardBlock {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val properties: EntityProperties = BlockEntityProperties(
            type = EntityTypes.InvisibleBlock
    )

    override val graphicsBehavior: IEntityGraphicsBehavior = object : DefaultEntityGraphicsBehavior() {
        override fun getImage(entity: Entity): Bitmap? {
            return null
        }
    }
}
