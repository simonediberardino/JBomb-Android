package com.diberardino.jbomb.domain.events.level.behavior

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.Player
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates

class GeneratePlayerBehavior(val coordinates: Coordinates): GameBehavior() {
    override fun hostBehavior(): () -> Unit {
        return {
            JBomb.match.player = Player(coordinates)
            JBomb.match.player!!.logic.spawn(forceSpawn = false, forceCentering = false)
        }
    }

    override fun clientBehavior(): () -> Unit {
        return {}
    }
}