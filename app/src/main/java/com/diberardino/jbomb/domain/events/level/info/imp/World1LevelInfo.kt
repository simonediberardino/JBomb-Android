package com.diberardino.jbomb.domain.events.level.info.imp

import com.diberardino.jbomb.domain.events.level.info.model.DefaultStoryLevelInfo
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.Boss

abstract class World1LevelInfo(level: Level): DefaultStoryLevelInfo(level) {
    override val worldId: Int get() = 1

    override val boss: Boss? get() = null
    override val maxDestroyableBlocks: Int get() = 10
}