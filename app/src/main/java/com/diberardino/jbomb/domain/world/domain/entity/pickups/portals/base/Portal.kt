package com.diberardino.jbomb.domain.world.domain.pickups.portals.base

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityImageModel
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.pickups.portals.base.logic.PortalLogic
import com.diberardino.jbomb.domain.world.domain.pickups.portals.base.state.PortalState
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp

abstract class Portal : PowerUp {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val state: PortalState = PortalState(entity = this)
    override val image: EntityImageModel = EntityImageModel(entity = this)
    abstract override val logic: PortalLogic
    override val tag: String? = null
}