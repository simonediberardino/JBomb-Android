package com.diberardino.jbomb.domain.world.domain.pickups.portals.base.logic

import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.pickups.portals.base.Portal
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.logic.PowerUpLogic

abstract class PortalLogic(override val entity: Portal) : PowerUpLogic(entity = entity) {
    override fun doApply(player: BomberEntity) {
    }

    override fun cancel(player: BomberEntity) {}
}