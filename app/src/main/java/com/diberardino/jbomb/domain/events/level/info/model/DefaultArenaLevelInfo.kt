package com.diberardino.jbomb.domain.events.level.info.model

import com.diberardino.jbomb.domain.events.level.levels.ArenaLevel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy
import com.diberardino.jbomb.localization.Localization
import kotlin.math.min

abstract class DefaultArenaLevelInfo(level: ArenaLevel): DefaultLevelInfo(level) {
    override val isArenaLevel: Boolean get() = true

    override val diedMessage: String get() = Localization.get(Localization.ARENA_DIED).replace("%rounds%", ArenaLevel.CURR_ROUND.toString())
    override val bossMaxHealth: Int get() = super.bossMaxHealth / 2

    override val startEnemiesCount: Int get() {
            return min(ArenaLevel.MIN_ENEMIES_COUNT + (level as ArenaLevel).currentRound.get(), ArenaLevel.MAX_ENEMIES_COUNT)
        }

    abstract val specialRoundEnemies: Array<Class<out Enemy>>
}