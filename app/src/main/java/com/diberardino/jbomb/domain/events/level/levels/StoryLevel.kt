package com.diberardino.jbomb.domain.events.level.levels

import com.diberardino.jbomb.domain.events.game.RoundPassedGameEvent
import com.diberardino.jbomb.localization.Localization
import com.diberardino.jbomb.localization.Localization.WORLD_NAME

abstract class StoryLevel : Level() {
    override fun endLevel() {
        try {
            //DataInputOutput.getInstance().setLastLevel(info.nextLevel!!.getConstructor().newInstance())
            //DataInputOutput.getInstance().increaseRounds()
            // DataInputOutput.getInstance().updateStoredPlayerData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        RoundPassedGameEvent().invoke(null)
    }

    override fun toString(): String {
        val formattedString = Localization.get(WORLD_NAME)
        return formattedString
                .replace("%world_id%", info.worldId.toString())
                .replace("%level_id%", info.levelId.toString())
    }

    override fun onStartLevel() {}
}