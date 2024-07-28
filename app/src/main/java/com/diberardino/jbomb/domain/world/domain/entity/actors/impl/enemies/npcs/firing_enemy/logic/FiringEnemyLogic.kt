package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.firing_enemy.logic

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.network.events.FireEventForwarder
import com.diberardino.jbomb.utility.Utility
import com.diberardino.jbomb.utility.now
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ai_enemy.logic.AiEnemyLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.firing_enemy.FiringEnemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.PistolExplosion
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.AbstractExplosion
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.handler.ExplosionHandler
import com.diberardino.jbomb.domain.world.domain.geo.Direction
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FiringEnemyLogic(override val entity: FiringEnemy) : AiEnemyLogic(entity = entity) {
    override fun process() {
        super.process()

        // Check if it's time to update the shooting behavior
        if (Utility.timePassed(entity.state.lastFire) <= entity.state.shootingRefreshRate)
            return

        if (Utility.timePassed(entity.state.spawnTime) <= entity.state.shootingRefreshRate)
            return

        // Check if the entity can shoot and if a random probability allows shooting
        if (!entity.state.canShoot) {
            return
        }

        Utility.runPercentage(entity.state.shootingChance) {
            val direction = entity.state.direction
            fire(direction)
        }
    }

    fun fire(direction: Direction) {
        val currentTime = now()
        entity.state.lastFire = currentTime

        // Calculate new coordinates with an explosion offset for vertical directions
        var newCoords = Coordinates.getNewTopLeftCoordinatesOnDirection(
                entity.info.position,
                direction,
                AbstractExplosion.SIZE
        )

        if (direction == Direction.UP || direction == Direction.DOWN) {
            val x = newCoords.x + AbstractExplosion.SPAWN_OFFSET
            newCoords = Coordinates(x, newCoords.y)
        }

        ExplosionHandler.instance.process {
            listOf(
                PistolExplosion(
                    owner = entity,
                    coordinates = newCoords,
                    direction = direction,
                    explosive = entity
            ).logic.explode())
        }

        if (JBomb.match.isServer) {
            FireEventForwarder().invoke(entity.toEntityNetwork(), direction)
        }

        entity.state.canMove = false

        JBomb.match.scope.launch {
            delay(2000)
            entity.state.canMove = true
        }

        entity.state.canShoot = true
    }
}