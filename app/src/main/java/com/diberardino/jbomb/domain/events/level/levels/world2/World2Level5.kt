package com.diberardino.jbomb.domain.events.level.levels.world2

import com.diberardino.jbomb.domain.events.level.info.imp.World2levelInfo
import com.diberardino.jbomb.domain.events.level.info.model.LevelInfo
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.events.level.levels.StoryLevel
import com.diberardino.jbomb.domain.events.level.levels.lobby.WorldSelectorLevel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.Boss
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.Clown
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.eagle.Eagle
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.fast_enemy.FastPurpleBall
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates

class World2Level5 : StoryLevel() {
    override val info: LevelInfo
        get() = object: World2levelInfo(this) {
            override val levelId: Int get() = 5
            override val boss: Boss get() = Clown()
            override val startEnemiesCount: Int get() = 5
            override val availableEnemies: Array<Class<out Enemy>> get() = arrayOf(FastPurpleBall::class.java, Eagle::class.java)
            override val isLastLevelOfWorld: Boolean get() = true
            override val nextLevel: Class<out Level?> get() = WorldSelectorLevel::class.java
            override val playerSpawnCoordinates: Coordinates get() = Coordinates.roundCoordinates(Coordinates(0, 0), BomberEntity.SPAWN_OFFSET)
        }
}