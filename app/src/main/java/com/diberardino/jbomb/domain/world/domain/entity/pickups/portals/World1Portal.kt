package com.diberardino.jbomb.domain.world.domain.pickups.portals

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.PeriodicGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.pickups.portals.imp.world_base.WorldPortal
import com.diberardino.jbomb.domain.world.domain.pickups.portals.imp.world_base.state.WorldPortalState
import game.domain.world.types.EntityTypes
import game.presentation.ui.panels.game.PitchPanel
import java.awt.Dimension

class World1Portal() : WorldPortal(null, 1) {
    constructor(id: Long) : this() {
        this.info.id = id
    }

    override val state: WorldPortalState = object : WorldPortalState(entity = this) {
        override val defaultCoords: Coordinates?
            get() = Coordinates.fromRowAndColumnsToCoordinates(Dimension(3, 4), 0, -PitchPanel.GRID_SIZE / 2)
    }

    override val properties: EntityProperties = EntityProperties(type = EntityTypes.World1Portal)

    override val graphicsBehavior: IEntityGraphicsBehavior = object: PeriodicGraphicsBehavior() {
        override val imagesCount: Int
            get() = 3
        override val allowUiState: Boolean
            get() = false
    }
}