package com.diberardino.jbomb.domain.level.levels.world1

import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.yellow_ball.YellowBall
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.zombie.Zombie
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.events.level.levels.StoryLevel
import com.diberardino.jbomb.domain.events.level.info.model.LevelInfo
import com.diberardino.jbomb.domain.events.level.info.imp.World1LevelInfo
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.skeleton.SkeletonEnemy

class World1Level4 : StoryLevel() {
    override val info: LevelInfo
        get() = object : World1LevelInfo(this) {
            override val levelId: Int get() = 4
            override val startEnemiesCount: Int get() = 6
            override val availableEnemies: Array<Class<out Enemy>> get() = arrayOf(YellowBall::class.java, SkeletonEnemy::class.java, Zombie::class.java)
            override val nextLevel: Class<out Level?> get() = World1Level5::class.java
        }
}