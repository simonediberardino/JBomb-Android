package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bonus.mystery_box

import game.JBomb
import game.domain.level.levels.Level
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bonus.mystery_box.base.MysteryBox
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bonus.mystery_box.base.logic.MysteryBoxLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bonus.mystery_box.base.state.MysteryBoxState
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp

class MysteryBoxPerk(
        level: () -> Level?,
        entity: () -> BomberEntity?
) : MysteryBox() {
    // TODO Refactor
    constructor(id: Long) : this({ JBomb.match.currentLevel }, { JBomb.match.player }) {
        this.info.id = id
    }

    override val logic: MysteryBoxLogic = object : MysteryBoxLogic(entity = this) {
        override fun onPurchaseConfirm() {
            val level = level() ?: return

            // Retrieve the active power-ups of the buyer, if any.
            val activePowerUps = state.buyer()?.state?.temporaryActivePowerUps
            val allowedPerks = level.info.allowedPerks.copyOf()

            allowedPerks.toMutableList().removeAll(activePowerUps ?: listOf())

            if (allowedPerks.isEmpty())
                return

            val powerUpClass = allowedPerks.random()

            val powerUpInstance: PowerUp = try {
                powerUpClass.getConstructor(Coordinates::class.java).newInstance(Coordinates(0, 0))
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

            powerUpInstance.logic.apply(JBomb.match.player ?: return)
        }
    }

    override val state: MysteryBoxState = object : MysteryBoxState(
            entity = this,
            buyer = entity,
            level = level
    ) {
        override val price: Int = 200
    }
}
