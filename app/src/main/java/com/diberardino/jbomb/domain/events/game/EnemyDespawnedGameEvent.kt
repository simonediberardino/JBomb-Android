package com.diberardino.jbomb.domain.events.game

import game.domain.events.models.GameEvent

class EnemyDespawnedGameEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        //if (!JBomb.match.gameState)
        //return
        //JBomb.match.currentLevel!!.eventHandler.onEnemyDespawned()
    }
}