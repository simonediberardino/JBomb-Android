package com.diberardino.jbomb.domain.events.game

import com.diberardino.jbomb.domain.events.models.GameEvent

class DeathGameEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        //JBomb.match.currentLevel.eventHandler.onDeathGameEvent()
        //JBomb.match.player = null
    }
}