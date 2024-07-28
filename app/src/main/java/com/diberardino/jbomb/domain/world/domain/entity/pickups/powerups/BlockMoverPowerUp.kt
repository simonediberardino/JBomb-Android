package com.diberardino.jbomb.domain.world.domain.pickups.powerups

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import game.domain.world.types.EntityTypes
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.movable_block.MovableBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.logic.PowerUpLogic
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.state.PowerUpState
import game.localization.Localization
import game.utils.file_system.Paths.powerUpsFolder
import java.awt.image.Bitmap

class BlockMoverPowerUp : PowerUp {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val graphicsBehavior: IEntityGraphicsBehavior = object : DefaultEntityGraphicsBehavior() {
        override fun getImage(entity: Entity): Bitmap? = loadAndSetImage(entity, "$powerUpsFolder/hand.png")
    }

    override val state: PowerUpState = object : PowerUpState(entity = this) {
        override val duration: Int = 30
    }

    override val logic: PowerUpLogic = object : PowerUpLogic(entity = this) {
        override fun doApply(player: BomberEntity) {
            player.logic.addClassInteractWithMouseDrag(MovableBlock::class.java)
        }

        override fun cancel(player: BomberEntity) {
            player.logic.removeClassInteractWithDrag(MovableBlock::class.java)
        }

    }

    override val properties: EntityProperties = EntityProperties(type = EntityTypes.BlockMoverPowerUp)
    override val tag: String
        get() = Localization.get(Localization.BLOCK_MOVER_POWERUP)

    init {
        state.incompatiblePowerUps += HammerPowerUp::class.java
    }
}
