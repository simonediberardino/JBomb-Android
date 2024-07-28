package com.diberardino.jbomb.domain.world.domain.pickups.powerups

import game.data.data.DataInputOutput
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.logic.PowerUpLogic
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.state.PowerUpState
import game.domain.world.types.EntityTypes
import game.localization.Localization
import game.utils.file_system.Paths.powerUpsFolder
import java.awt.image.Bitmap

class LivesPowerUp : PowerUp {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val graphicsBehavior: IEntityGraphicsBehavior = object : DefaultEntityGraphicsBehavior() {
        override fun getImage(entity: Entity): Bitmap? = loadAndSetImage(entity, "$powerUpsFolder/lives_up.png")
    }

    override val logic: PowerUpLogic = object : PowerUpLogic(entity = this) {
        override fun doApply(player: BomberEntity) {
            player.logic.restoreHealth()
            DataInputOutput.getInstance().increaseLives()
        }

        override fun cancel(player: BomberEntity) {}

        
    }

    override val state: PowerUpState = object : PowerUpState(entity = this) {
        override val duration: Int = 0
        override val isDisplayable: Boolean = false
    }

    override val tag: String
        get() = Localization.get(Localization.HP_POWERUP)

    override val properties: EntityProperties = EntityProperties(type = EntityTypes.LivesPowerUp)
}
