package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.graphics

import android.graphics.Bitmap
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior

class DestroyableBlockGraphics : DefaultEntityGraphicsBehavior() {
    override fun getImage(entity: Entity): Bitmap? {
        return loadAndSetImage(
                entity = entity,
                imagePath = JBomb.match.currentLevel!!.info.destroyableBlockImagePath
        )
    }
}