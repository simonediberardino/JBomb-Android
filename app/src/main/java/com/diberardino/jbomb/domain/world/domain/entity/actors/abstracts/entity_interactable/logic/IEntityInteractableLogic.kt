package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable.logic

import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityLogic
import com.diberardino.jbomb.domain.world.domain.geo.Direction
import game.presentation.ui.panels.game.PitchPanel

interface IEntityInteractableLogic : IEntityLogic {
    fun moveOrInteract(direction: Direction, stepSize: Int, ignoreMapBorders: Boolean): Boolean
    fun moveOrInteract(direction: Direction, stepSize: Int = PitchPanel.PIXEL_UNIT): Boolean
    fun move(coordinates: Coordinates)
    fun onMove(coordinates: Coordinates)
    fun attack(e: Entity?)
    fun isObstacle(e: Entity?): Boolean
    fun canInteractWith(e: Entity?): Boolean
    fun interactAndUpdateLastInteract(e: Entity?)
}