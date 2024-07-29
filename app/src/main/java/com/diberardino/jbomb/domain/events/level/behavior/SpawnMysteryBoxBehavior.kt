package com.diberardino.jbomb.domain.events.level.behavior

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bonus.mystery_box.MysteryBoxPerk
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.values.Dimensions.GRID_SIZE

class SpawnMysteryBoxBehavior(val level: Level) : GameBehavior() {
    override fun hostBehavior(): () -> Unit {
        return {
            spawnMysteryBox()
        }
    }

    override fun clientBehavior(): () -> Unit {
        return {}
    }

    private fun spawnMysteryBox() {
        val player = JBomb.match.player ?: return
        val c = Coordinates.generateCoordinatesAwayFrom(player.info.position, GRID_SIZE * 2)
        val mysteryBox: Entity = MysteryBoxPerk({ level }, { JBomb.match.player })
        mysteryBox.info.position = c
        mysteryBox.logic.spawn()
    }
}