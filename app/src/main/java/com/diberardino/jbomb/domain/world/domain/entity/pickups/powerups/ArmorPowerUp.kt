package com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups

import android.graphics.Bitmap
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.base.logic.PowerUpLogic
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.localization.Localization
import com.diberardino.jbomb.utility.Paths.powerUpsFolder

class ArmorPowerUp : PowerUp {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val logic: PowerUpLogic = object : PowerUpLogic(entity = this) {
        override fun doApply(player: BomberEntity) {
            player.state.isImmune = true
            player.logic.onImmuneChangedState()
        }

        override fun cancel(player: BomberEntity) {
            if (player.state.isSpawned) {
                player.state.isImmune = false
                player.logic.onImmuneChangedState()
            }
        }
    }

    override val graphicsBehavior : IEntityGraphicsBehavior = object : DefaultEntityGraphicsBehavior() {
        override fun getImage(entity: Entity): Bitmap? = loadAndSetImage(entity, "$powerUpsFolder/armor_up.png")
    }

    override val properties: EntityProperties = EntityProperties(type = EntityTypes.ArmorPowerUp)
    override val tag: String
        get() = Localization.get(Localization.ARMOR_POWER_UP)
}