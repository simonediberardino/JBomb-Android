package com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.logic

import game.JBomb
import game.audio.AudioManager
import game.audio.SoundModel
import game.domain.tasks.observer.Observable2
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable.logic.EntityInteractableLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import game.utils.dev.Log
import java.util.*

abstract class PowerUpLogic(
        override val entity: PowerUp
) : EntityInteractableLogic(entity = entity), IPowerUpLogic {
    override fun doInteract(e: Entity?) {
        apply(e as BomberEntity)
    }

    override fun damageAnimation() {}

    final override fun apply(player: BomberEntity) {
        if (this.entity.state.applied || !canPickUp(player))
            return

        entity.state.applied = true
        eliminated()
        entity.state.bomberEntity = player

        doApply(player)

        val matchPanel = JBomb.JBombFrame.matchPanel
        AudioManager.instance.play(SoundModel.POWERUP)

        player.logic.onPowerupApply(entity)

        if (!entity.state.isPermanent)
            player.state.temporaryActivePowerUps.add(entity.javaClass)

        player.state.activePowerUps.add(entity.javaClass)

        if (entity.state.isDisplayable)
            matchPanel.refreshPowerUps(player.state.activePowerUps)

        val durationMillis: Long = entity.state.duration * 1000L

        // If the power-up has a duration, schedule a TimerTask to cancel it when the duration is up
        if (entity.state.isPermanent)
            return

        val task = object : TimerTask() {
            override fun run() {
                val match = JBomb.match ?: return
                if (!match.gameState) return

                player.state.activePowerUps.remove(entity.javaClass)
                player.state.temporaryActivePowerUps.remove(entity.javaClass)

                if (entity.state.isDisplayable)
                    matchPanel.refreshPowerUps(player.state.activePowerUps)

                cancel(player)
            }
        }

        Timer().schedule(task, durationMillis)
    }

    override fun canPickUp(bomberEntity: BomberEntity): Boolean {
        Log.e("active power ups: ${bomberEntity.state.activePowerUps}")

        return bomberEntity.state.activePowerUps.all {
            Log.e("it class is ${it::class.java}, entity class is ${this.entity.javaClass}")

            if (it == this.entity.javaClass && !entity.state.isPermanent) {
                return@all false
            }

            if (this.entity.state.incompatiblePowerUps.contains(it)) {
                return@all false
            }

            true
        }
    }

    override fun onAttackReceived(damage: Int) {}

    override fun observerUpdate(arg: Observable2.ObserverParam) {}

    override fun onMove(coordinates: Coordinates) {}

}