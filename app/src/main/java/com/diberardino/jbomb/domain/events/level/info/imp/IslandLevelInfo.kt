package game.domain.level.info.imp

import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import game.domain.level.levels.Level
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import java.awt.Dimension

class IslandLevelInfo(level: Level) : LobbyLevelInfo(level) {
    override val worldId: Int get() = 0
    override val levelId: Int get() = 0

    override val playerSpawnCoordinates: Coordinates
        get() = Coordinates.fromRowAndColumnsToCoordinates(Dimension(5, 2), 0, 0)

    override val startAnimalsCount: Int
        get() = 2
}