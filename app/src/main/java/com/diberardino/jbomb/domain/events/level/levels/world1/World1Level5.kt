package com.diberardino.jbomb.domain.level.levels.world1

import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.Boss
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.ghost.GhostBoss
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.yellow_ball.YellowBall
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.zombie.Zombie
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.events.level.levels.StoryLevel
import com.diberardino.jbomb.domain.events.level.info.model.LevelInfo
import com.diberardino.jbomb.domain.events.level.info.imp.World1LevelInfo
import com.diberardino.jbomb.domain.level.levels.world2.World2Level1
import java.awt.Dimension

class World1Level5 : StoryLevel() {
    override val info: LevelInfo
        get() = object : World1LevelInfo(this) {
            override val levelId: Int get() = 5
            override val boss: Boss get() = GhostBoss()
            override val startEnemiesCount: Int get() = 0
            override val availableEnemies: Array<Class<out Enemy>> get() = arrayOf(YellowBall::class.java, Zombie::class.java)
            override val isLastLevelOfWorld: Boolean get() = true
            override val nextLevel: Class<out Level?> get() = World2Level1::class.java
            override val playerSpawnCoordinates: Coordinates
                get() = Coordinates.fromRowAndColumnsToCoordinates(
                            Dimension(0,
                                    matchPanelSize.height.toInt() / GRID_SIZE - 1)
                    )
        }
}