package com.diberardino.jbomb.domain.events.game

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.GameEvent

class AllEnemiesEliminatedGameEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        if (JBomb.isGameEnded)
            return

        JBomb.match.currentLevel.eventHandler.onAllEnemiesEliminated()
    }
}