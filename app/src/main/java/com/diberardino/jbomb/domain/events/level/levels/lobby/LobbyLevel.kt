package com.diberardino.jbomb.domain.level.levels.lobby

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.level.gamehandler.imp.DefaultGameHandler
import com.diberardino.jbomb.domain.level.gamehandler.model.GameHandler
import com.diberardino.jbomb.domain.events.level.levels.StoryLevel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.Player

abstract class LobbyLevel : StoryLevel() {
    override val gameHandler: GameHandler
        get() = object : DefaultGameHandler(this) {
            override fun generate() {
                generateLevel()
                JBomb.match.player = Player(info.playerSpawnCoordinates)
                JBomb.match.player!!.logic.spawn(forceSpawn = false, forceCentering = false)
                spawnAnimals()
            }
        }

    abstract fun generateLevel()
}