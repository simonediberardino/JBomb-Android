package com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups

import androidx.compose.ui.graphics.ImageBitmap
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.DestroyableBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.base.logic.PowerUpLogic
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.state.PowerUpState
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.localization.Localization
import com.diberardino.jbomb.utility.Paths.powerUpsFolder

class HammerPowerUp : PowerUp {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val logic: PowerUpLogic = object : PowerUpLogic(entity = this) {
        override fun doApply(player: BomberEntity) {
            player.logic.addClassInteractWithMouseClick(DestroyableBlock::class.java)
        }

        override fun cancel(player: BomberEntity) {
            player.logic.removeClassInteractWithMouseClick(DestroyableBlock::class.java)
        }
    }

    override val graphicsBehavior: IEntityGraphicsBehavior = object : DefaultEntityGraphicsBehavior() {
        override fun getImage(entity: Entity): ImageBitmap? {
            return loadAndSetImage(entity, "$powerUpsFolder/hammer.png")
        }
    }

    override val properties: EntityProperties = EntityProperties(type = EntityTypes.HammerPowerUp)

    override val state: PowerUpState = object : PowerUpState(entity = this) {
        override val duration: Int
            get() = 30
    }

    override val tag: String
        get() = Localization.get(Localization.HAMMER_POWERUP)

    init {
        state.incompatiblePowerUps += BlockMoverPowerUp::class.java
    }
}