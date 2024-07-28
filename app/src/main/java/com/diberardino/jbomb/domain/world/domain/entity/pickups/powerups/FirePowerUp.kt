package com.diberardino.jbomb.domain.world.domain.pickups.powerups

import game.domain.world.types.EntityTypes
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.AbstractExplosion.Companion.MAX_EXPLOSION_LENGTH
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.events.game.ExplosionLengthPowerUpEvent
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.logic.PowerUpLogic
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.state.PowerUpState
import game.localization.Localization
import game.utils.file_system.Paths.powerUpsFolder
import java.awt.image.Bitmap

class FirePowerUp : PowerUp {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val graphicsBehavior: IEntityGraphicsBehavior = object : DefaultEntityGraphicsBehavior() {
        override fun getImage(entity: Entity): Bitmap? = loadAndSetImage(entity, "$powerUpsFolder/fire_up.png")
    }

    override val logic: PowerUpLogic = object : PowerUpLogic(entity = this) {
        override fun doApply(player: BomberEntity) {
            ExplosionLengthPowerUpEvent().invoke(player)
        }

        override fun cancel(player: BomberEntity) {}

        override fun canPickUp(bomberEntity: BomberEntity): Boolean = bomberEntity.state.currExplosionLength <= MAX_EXPLOSION_LENGTH && super.canPickUp(bomberEntity)
    }

    override val state: PowerUpState = object : PowerUpState(entity = this) {
        override val duration: Int = 0
    }

    override val tag: String
        get() = Localization.get(Localization.FIRE_POWERUP)

    override val properties: EntityProperties = EntityProperties(type = EntityTypes.FirePowerUp)
}
