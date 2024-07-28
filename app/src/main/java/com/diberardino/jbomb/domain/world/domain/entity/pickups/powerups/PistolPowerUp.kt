package com.diberardino.jbomb.domain.world.domain.pickups.powerups

import game.JBomb
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import game.domain.world.types.EntityTypes
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.items.PistolItem
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.logic.PowerUpLogic
import game.localization.Localization
import game.utils.file_system.Paths.itemsPath
import java.awt.image.Bitmap

class PistolPowerUp : PowerUp {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val graphicsBehavior: IEntityGraphicsBehavior = object : DefaultEntityGraphicsBehavior() {
        override fun getImage(entity: Entity): Bitmap? = loadAndSetImage(entity, "$itemsPath/pistol.png")
    }

    override val logic: PowerUpLogic = object : PowerUpLogic(entity = this) {
        override fun doApply(player: BomberEntity) {
            JBomb.match.give(player, PistolItem(), true)
        }

        override fun cancel(player: BomberEntity) {
            JBomb.match.removeItem(player)
        }

        
    }

    override val tag: String
        get() = Localization.get(Localization.PISTOL_POWERUP)

    override val properties: EntityProperties = EntityProperties(type = EntityTypes.PistolPowerUp)
}
