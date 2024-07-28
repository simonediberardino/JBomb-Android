package com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.logic

import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity

interface IPowerUpLogic {
    /**
     * Applies the power-up to the specified BomberEntity.
     *
     * @param player the BomberEntity to apply the power-up to
     */
    fun apply(player: BomberEntity)
    /**
     * Applies the power-up to the specified BomberEntity. This method should be implemented by the subclasses.
     *
     * @param player the BomberEntity to apply the power-up to
     */
    fun doApply(player: BomberEntity)
    /**
     * Cancels the power-up applied to the specified BomberEntity. This method should be implemented by the subclasses.
     *
     * @param player the BomberEntity to cancel the power-up for
     */
    fun cancel(player: BomberEntity)
    /**
     * @return whether the powerup can be picked up indefinite times or not;
     */
    fun canPickUp(bomberEntity: BomberEntity): Boolean
}