package com.diberardino.jbomb.domain.events.level.levels.world1

import com.diberardino.jbomb.domain.events.level.info.imp.World1LevelInfo
import com.diberardino.jbomb.domain.events.level.info.model.LevelInfo
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.events.level.levels.StoryLevel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.helicopter.Helicopter

class World1Level2 : StoryLevel() {
    override val info: LevelInfo
        get() = object : World1LevelInfo(this) {
            override val levelId: Int get() = 2
            override val startEnemiesCount: Int get() = 5
            override val availableEnemies: Array<Class<out Enemy>> get() = arrayOf(Helicopter::class.java)
            override val nextLevel: Class<out Level?> get() = World1Level3::class.java
        }
}