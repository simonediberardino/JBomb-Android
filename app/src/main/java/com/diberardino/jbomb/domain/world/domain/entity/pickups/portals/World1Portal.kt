package com.diberardino.jbomb.domain.world.domain.entity.pickups.portals

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.PeriodicGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.pickups.portals.imp.world_base.WorldPortal
import com.diberardino.jbomb.domain.world.domain.entity.pickups.portals.imp.world_base.state.WorldPortalState
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.values.Dimension
import com.diberardino.jbomb.values.Dimensions.GRID_SIZE

class World1Portal() : WorldPortal(null, 1) {
    constructor(id: Long) : this() {
        this.info.id = id
    }

    override val state: WorldPortalState = object : WorldPortalState(entity = this) {
        override val defaultCoords: Coordinates?
            get() = Coordinates.fromRowAndColumnsToCoordinates(Dimension(3, 4), 0, -GRID_SIZE / 2)
    }

    override val properties: EntityProperties = EntityProperties(type = EntityTypes.World1Portal)

    override val graphicsBehavior: IEntityGraphicsBehavior = object: PeriodicGraphicsBehavior() {
        override val imagesCount: Int
            get() = 3
        override val allowUiState: Boolean
            get() = false
    }
}