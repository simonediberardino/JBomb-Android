package com.diberardino.jbomb.domain.world.domain.pickups.powerups

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import game.domain.world.types.EntityTypes
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.logic.PowerUpLogic
import game.localization.Localization
import game.utils.file_system.Paths.powerUpsFolder
import java.awt.image.Bitmap

class RemoteControlPowerUp
/**
 * Constructs a PowerUp entity with the specified coordinates.
 *
 * @param coordinates the coordinates of the PowerUp entity
 */
 : PowerUp {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val graphicsBehavior: IEntityGraphicsBehavior = object : DefaultEntityGraphicsBehavior() {
        override fun getImage(entity: Entity): Bitmap? = loadAndSetImage(entity, "$powerUpsFolder/remote_up.png")
    }

    override val logic: PowerUpLogic = object : PowerUpLogic(entity = this) {
        override fun doApply(player: BomberEntity) {
            player.logic.addClassInteractWithMouseClick(Bomb::class.java)
        }

        override fun cancel(player: BomberEntity) {
            player.logic.removeClassInteractWithMouseClick(Bomb::class.java)
        }

        
    }

    override val tag: String
        get() = Localization.get(Localization.RC_POWERUP)

    override val properties: EntityProperties = EntityProperties(type = EntityTypes.RemoteControlPowerUp)
}