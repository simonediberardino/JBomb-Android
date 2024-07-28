package com.diberardino.jbomb.domain.events.level.behavior

import game.domain.level.levels.Level

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
        /*val player = JBomb.match.player ?: return
        val c = Coordinates.generateCoordinatesAwayFrom(player.info.position, PitchPanel.GRID_SIZE * 2)
        val mysteryBox: Entity = MysteryBoxPerk({ level }, { JBomb.match.player })
        mysteryBox.info.position = c
        mysteryBox.logic.spawn()*/
    }
}