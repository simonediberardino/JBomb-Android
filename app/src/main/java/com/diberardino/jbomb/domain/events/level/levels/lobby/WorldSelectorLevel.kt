package com.diberardino.jbomb.domain.events.level.levels.lobby

import com.diberardino.jbomb.domain.world.domain.entity.pickups.portals.World2Portal
import com.diberardino.jbomb.domain.world.domain.entity.pickups.portals.World1Portal
import com.diberardino.jbomb.localization.Localization

class WorldSelectorLevel : IslandLevel() {
    override fun generateLevel() {
        super.generateLevel()
        generatePortals()
    }

    private fun generatePortals() {
        //val lastWorldId = 1.coerceAtLeast(DataInputOutput.getInstance().lastWorldId)

        val lastWorldId = 2
        val worldPortals = WORLDS_ID_TO_PORTAL
                .filterKeys { it <= lastWorldId }
                .values
                .mapNotNull {
                    try {
                        it.getDeclaredConstructor().newInstance()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        null
                    }
                }

        worldPortals.forEach { it.logic.spawn(forceSpawn = true, forceCentering = false) }
    }

    override fun toString(): String {
        return Localization.get(Localization.ISLAND)
    }

    companion object {
        val WORLDS_ID_TO_PORTAL = hashMapOf(
                1 to World1Portal::class.java,
                2 to World2Portal::class.java
        )
    }
}