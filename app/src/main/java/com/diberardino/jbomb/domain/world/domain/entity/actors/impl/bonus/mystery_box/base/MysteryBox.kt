package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bonus.mystery_box.base

import android.graphics.Bitmap
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.hard_block.HardBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bonus.mystery_box.base.logic.MysteryBoxLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bonus.mystery_box.base.state.MysteryBoxState
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.utility.Paths
import com.diberardino.jbomb.values.Dimensions.GRID_SIZE

abstract class MysteryBox : HardBlock(Coordinates(0, 0)) {
    abstract override val state: MysteryBoxState
    abstract override val logic: MysteryBoxLogic

    override val graphicsBehavior: IEntityGraphicsBehavior = object : DefaultEntityGraphicsBehavior() {
        override fun getImage(entity: Entity): Bitmap? {
            return loadAndSetImage(entity, "${Paths.powerUpsFolder}/box_${state.status.toString().lowercase()}.png")
        }
    }

    override val properties: EntityProperties = EntityProperties(type = EntityTypes.MysteryBoxPerk)

    enum class Status {
        CLOSED,
        OPEN
    }

    companion object {
        const val OPEN_BOX_TIME = 5000
        const val CONFIRM_DELAY_MS: Long = 5000
    }

    internal object DEFAULT {
        val SIZE = GRID_SIZE
    }
}
