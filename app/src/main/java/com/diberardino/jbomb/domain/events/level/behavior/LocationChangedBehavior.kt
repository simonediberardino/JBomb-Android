package com.diberardino.jbomb.domain.events.level.behavior

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.Player
import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.events.forward.LocationUpdatedHttpEventForwarder
import com.diberardino.jbomb.network.gamehandler.ClientGameHandler


class LocationChangedBehavior(private val entityNetwork: EntityNetwork) : GameBehavior() {
    override fun hostBehavior(): () -> Unit {
        return {
            LocationUpdatedHttpEventForwarder().invoke(entityNetwork, false)
        }
    }

    override fun clientBehavior(): () -> Unit {
        return {
            if (entityNetwork.entityId == (JBomb.match.onlineGameHandler as ClientGameHandler).id) {
                // If player is not stored yet
                val player = JBomb.match.player
                if (player == null || !player.state.isSpawned) {
                    (JBomb.match.getEntityById(entityNetwork.entityId) as Player?)?.let {
                        JBomb.match.player = it
                    }
                }

                LocationUpdatedHttpEventForwarder().invoke(entityNetwork, true)
            }
        }
    }
}