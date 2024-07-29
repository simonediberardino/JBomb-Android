package com.diberardino.jbomb.domain.level.levels.world2

import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.eagle.Eagle
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.fast_enemy.FastPurpleBall
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.events.level.levels.StoryLevel
import com.diberardino.jbomb.domain.events.level.info.model.LevelInfo
import com.diberardino.jbomb.domain.events.level.info.imp.World2levelInfo

class World2Level1 : StoryLevel() {
    override val info: LevelInfo
        get() = object : World2levelInfo(this) {
            override val levelId: Int
                get() {
                    return 1
                }

            override val startEnemiesCount: Int
                get() {
                    return 5
                }

            override val availableEnemies: Array<Class<out Enemy>>
                get() {
                    return arrayOf(
                            Eagle::class.java,
                            FastPurpleBall::class.java
                    )
                }

            override val nextLevel: Class<out Level?>
                get() {
                    return World2Level2::class.java
                }
        }

}