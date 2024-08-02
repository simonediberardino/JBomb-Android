package com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups

import androidx.compose.ui.graphics.ImageBitmap
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.base.logic.PowerUpLogic
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.localization.Localization
import com.diberardino.jbomb.utility.Paths.powerUpsFolder

class TransparentBombsPowerUp : PowerUp {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val graphicsBehavior: IEntityGraphicsBehavior = object : DefaultEntityGraphicsBehavior() {
        override fun getImage(entity: Entity): ImageBitmap? = loadAndSetImage(entity, "$powerUpsFolder/transparent_bomb_powerup.png")
    }

    override val logic: PowerUpLogic = object : PowerUpLogic(entity = this) {
        override fun doApply(player: BomberEntity) {
            player.state.forceBombsSolid = (true)
            player.state.bombsSolid = (false)
        }

        override fun cancel(player: BomberEntity) {
            player.state.forceBombsSolid = (false)
            player.state.bombsSolid = (true)
        }
    }

    override val tag: String
        get() = Localization.get(Localization.TRANSPARENT_BOMBS_POWERUP)

    override val properties: EntityProperties = EntityProperties(type = EntityTypes.TransparentBombsPowerUp)
}