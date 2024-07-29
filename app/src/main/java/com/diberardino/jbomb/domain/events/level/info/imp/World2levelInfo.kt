package com.diberardino.jbomb.domain.events.level.info.imp

import com.diberardino.jbomb.domain.events.level.info.model.DefaultStoryLevelInfo
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.Boss

abstract class World2levelInfo(level: Level): DefaultStoryLevelInfo(level) {
    override val worldId: Int get() = 2
    override val maxDestroyableBlocks: Int get() = 15
    override val boss: Boss? get() = null
}