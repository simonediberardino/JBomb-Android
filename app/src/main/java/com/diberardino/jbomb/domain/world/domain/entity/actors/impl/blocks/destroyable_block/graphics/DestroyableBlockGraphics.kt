package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.graphics

import game.JBomb
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import java.awt.image.Bitmap

class DestroyableBlockGraphics : DefaultEntityGraphicsBehavior() {
    override fun getImage(entity: Entity): Bitmap? {
        return loadAndSetImage(
                entity = entity,
                imagePath = JBomb.match.currentLevel!!.info.destroyableBlockImagePath
        )
    }
}