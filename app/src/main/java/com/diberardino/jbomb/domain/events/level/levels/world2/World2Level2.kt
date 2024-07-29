package com.diberardino.jbomb.domain.events.level.levels.world2

import com.diberardino.jbomb.domain.events.level.info.imp.World2levelInfo
import com.diberardino.jbomb.domain.events.level.info.model.LevelInfo
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.events.level.levels.StoryLevel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.eagle.Eagle
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.tank.TankEnemy

class World2Level2 : StoryLevel() {
    override val info: LevelInfo
        get() = object: World2levelInfo(this) {
            override val levelId: Int get() = 2
            override val startEnemiesCount: Int get() = 6
            override val availableEnemies: Array<Class<out Enemy>> get() = arrayOf(TankEnemy::class.java, Eagle::class.java)
            override val nextLevel: Class<out Level?> get() = World2Level3::class.java
        }
}