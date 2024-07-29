package com.diberardino.jbomb.domain.events.level.info.model

import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.localization.Localization

abstract class DefaultStoryLevelInfo(level: Level) : DefaultLevelInfo(level) {
    override val diedMessage: String
        get() {
            val lives = 3//DataInputOutput.getInstance().lives
            return Localization.get(Localization.YOU_DIED).replace("%lives%", lives.toString())
        }

    override val isArenaLevel: Boolean get() = false
}