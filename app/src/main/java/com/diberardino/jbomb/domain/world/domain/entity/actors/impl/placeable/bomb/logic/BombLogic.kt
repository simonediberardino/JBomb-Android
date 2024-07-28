package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.logic

import game.JBomb
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.logic.BlockEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.FireExplosion
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.AbstractExplosion
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.handler.ExplosionHandler
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb
import com.diberardino.jbomb.domain.world.domain.geo.Direction
import game.network.events.forward.BombExplodedEventForwarder
import kotlinx.coroutines.launch
import java.util.*

class BombLogic(override val entity: Bomb) : BlockEntityLogic(entity = entity), IBombLogic {
    private var exploded: Boolean = false
    var onExplodeCallback: (() -> Unit)? = null

    /**
     * Handles the interaction between the bomb and another entity.
     *
     * @param e The entity to interact with.
     */
    override fun doInteract(e: Entity?) {
        explode()
    }

    override fun onSpawn() {
        super.onSpawn()
        JBomb.match.gameTickerObservable?.register(entity)
    }

    override fun notifySpawn() {}

    override fun onDespawn() {
        super.onDespawn()
        JBomb.match.gameTickerObservable?.unregister(entity)
    }

    override fun notifyDespawn() {}

    override fun onAdded() {
        super.onAdded()
        JBomb.match.addBomb(entity)
    }

    override fun onRemoved() {
        super.onRemoved()
        JBomb.match.removeBomb(entity)
    }

    override fun onExplosion(explosion: AbstractExplosion?) {
        super.onExplosion(explosion)
        explode()
    }

    override fun explode() {
        if (exploded || JBomb.isGameEnded) {
            return
        }
        
        exploded = true
        eliminated()

        JBomb.match.scope.launch {
            // TODO CHANGED
            ExplosionHandler.instance.process {
                // Trigger explosions in all directions
                Direction.values().map {
                    FireExplosion(
                            entity.state.caller,
                            entity.info.position,
                            it,
                            entity
                    ).logic.explode()
                }
            }

            // Invoke the explosion callback if set
            onExplodeCallback?.invoke()
        }
    }

    override fun trigger() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                explode()
            }
        }, Bomb.EXPLODE_TIMER.toLong())
    }

    /**
     * Handles mouse click interaction with the bomb.
     */
    override fun onMouseClickInteraction() {
        explode()
        BombExplodedEventForwarder().invoke(entity.state.caller.toEntityNetwork(), entity.toEntityNetwork())
    }

    /**
     * Destroys the bomb, initiating the explosion.
     */
    override fun destroy() = explode()
}