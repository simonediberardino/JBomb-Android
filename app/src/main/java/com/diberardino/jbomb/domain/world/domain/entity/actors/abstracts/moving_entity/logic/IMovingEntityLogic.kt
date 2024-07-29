package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.logic

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable.logic.IEntityInteractableLogic
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction

interface IMovingEntityLogic : IEntityInteractableLogic {
    fun availableDirections(): List<Direction>
}