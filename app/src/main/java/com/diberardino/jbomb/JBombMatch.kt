package com.diberardino.jbomb

import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.Player
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb
import game.domain.level.levels.Level
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class JBombMatch(
    var currentLevel: Level,
    //val onlineGameHandler: OnlineGameHandler?
) {
    companion object {
        var defaultPort: Int = 30960
    }

    val scope = CoroutineScope(Dispatchers.IO)

    // Player information (nullable)
    var player: Player? = null

    var players: MutableList<BomberEntity> = mutableListOf()
        private set

    // List of bombs in the game
    val bombs = ArrayList<Bomb>()

    // Current game state (default: false)
    var gameState = false

    var pausePanelVisible = false

    // Number of enemies currently alive (read-only)
    var enemiesAlive = 0
        private set

    var isServer = true // TODO
}