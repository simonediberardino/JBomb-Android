package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.logic

import com.diberardino.jbomb.domain.events.level.behavior.GameBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.Boss
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ai_enemy.logic.AiEnemyLogic
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.pickups.portals.EndLevelPortal
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp

open class BossEntityLogic(override val entity: Boss) : AiEnemyLogic(entity = entity),
    IBossEntityLogic {
    override fun onDespawn() {
        super.onDespawn()

        val gameBehavior: GameBehavior = object : GameBehavior() {
            override fun hostBehavior(): () -> Unit {
                return {
                    val endLevelPortal: PowerUp = EndLevelPortal(Coordinates.generateCoordinatesAwayFromPlayers())
                    endLevelPortal.logic.spawn(true)
                }
            }

            override fun clientBehavior(): () -> Unit {
                return {}
            }
        }

        gameBehavior.invoke()
    }

    override fun onSpawn() {
        super.onSpawn()
        val gameBehavior: GameBehavior = object : GameBehavior() {
            override fun hostBehavior(): () -> Unit {
                return {
                    move((Coordinates.randomCoordinatesFromPlayer(entity.state.size, entity.state.size * 2)))
                }
            }

            override fun clientBehavior(): () -> Unit {
                return {}
            }

        }
        gameBehavior.invoke()
    }

    override fun onEliminated() {
        super.onEliminated()
        //AudioManager.instance.play(SoundModel.BOSS_DEATH)
    }

    /**
     * Updates the rage status of the Boss, loading and setting the corresponding image.
     *
     * @param status the new rage status to be set.
     */
    override fun updateRageStatus(status: Int) {
        // If the new rage status is the same as the current one, nothing to update.
        if (status == entity.state.currRageStatus)
            return

        entity.state.currRageStatus = status
        // Load and set the image.
        entity.graphicsBehavior.loadAndSetImage(
                entity = entity,
                imagePath = entity.graphicsBehavior.getImageFromRageStatus()
        )
    }
}