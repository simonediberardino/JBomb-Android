package com.diberardino.jbomb

object JBomb {
    lateinit var match: JBombMatch
        private set

    val isGameEnded: Boolean
        get() = !match.gameState || !JBomb.isInGame

    val isInGame: Boolean
        get() {
            val match = match
            val currentLevel = match.currentLevel
            return match.gameState && currentLevel.info.worldId >= 0
        }
}