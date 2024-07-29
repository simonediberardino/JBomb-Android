package com.diberardino.jbomb.domain.events.level.levels.world2

import com.diberardino.jbomb.domain.events.level.info.model.DefaultArenaLevelInfo
import com.diberardino.jbomb.domain.events.level.info.model.LevelInfo
import com.diberardino.jbomb.domain.events.level.levels.ArenaLevel
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.Boss
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.Clown
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.eagle.Eagle
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.fast_enemy.FastPurpleBall
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.skeleton.SkeletonEnemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.tank.TankEnemy

class World2Arena : ArenaLevel() {
    override val info: LevelInfo
        get() = object : DefaultArenaLevelInfo(this) {
            override val specialRoundEnemies: Array<Class<out Enemy?>> get() = arrayOf(TankEnemy::class.java, SkeletonEnemy::class.java)
            override val boss: Boss get() = Clown()
            override val maxDestroyableBlocks: Int get() = 10
            override val nextLevel: Class<out Level>? get() = null
            override val availableEnemies: Array<Class<out Enemy>> get() = arrayOf(
                    FastPurpleBall::class.java,
                    Eagle::class.java,
                    SkeletonEnemy::class.java
            )
            override val worldId: Int get() = 2
            override val levelId: Int get() = 0
        }
}