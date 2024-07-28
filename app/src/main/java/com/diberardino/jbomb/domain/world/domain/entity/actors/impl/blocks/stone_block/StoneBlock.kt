package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.stone_block

import game.JBomb
import game.domain.world.types.EntityTypes
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.properties.BlockEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.hard_block.HardBlock
import java.awt.image.Bitmap

class StoneBlock : HardBlock {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val properties: EntityProperties = BlockEntityProperties(type = EntityTypes.StoneBlock)

    override val graphicsBehavior: IEntityGraphicsBehavior = object : DefaultEntityGraphicsBehavior() {
        override fun getImage(entity: Entity): Bitmap? {
            return loadAndSetImage(
                    entity = entity,
                    imagePath = JBomb.match.currentLevel!!.info.stoneBlockImagePath
            )
        }
    }
}
