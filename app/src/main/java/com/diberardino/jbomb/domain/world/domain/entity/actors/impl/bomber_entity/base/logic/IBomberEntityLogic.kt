package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.logic

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.logic.ICharacterEntityLogic
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp

interface IBomberEntityLogic : ICharacterEntityLogic {
    fun isMouseDragInteractable(entity: Entity): Boolean
    fun isMouseClickInteractable(e: Entity): Boolean
    fun addClassInteractWithMouseClick(cls: Class<out Entity>)
    fun addClassInteractWithMouseDrag(cls: Class<out Entity>)
    fun removeClassInteractWithMouseClick(cls: Class<out Entity>)
    fun removeClassInteractWithDrag(cls: Class<out Entity>)
    fun removeActivePowerUp(p: PowerUp?)
    fun onPowerupApply(powerUp: PowerUp)
}