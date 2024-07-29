package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable.logic

import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.values.Dimensions.PIXEL_UNIT

interface IEntityInteractableLogic : IEntityLogic {
    fun moveOrInteract(direction: Direction, stepSize: Int, ignoreMapBorders: Boolean): Boolean
    fun moveOrInteract(direction: Direction, stepSize: Int = PIXEL_UNIT): Boolean
    fun move(coordinates: Coordinates)
    fun onMove(coordinates: Coordinates)
    fun attack(e: Entity?)
    fun isObstacle(e: Entity?): Boolean
    fun canInteractWith(e: Entity?): Boolean
    fun interactAndUpdateLastInteract(e: Entity?)
}