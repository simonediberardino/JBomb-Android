package com.diberardino.jbomb.domain.events.game

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.GameEvent
import com.diberardino.jbomb.network.events.forward.UpdateInfoEventForwarder

class UpdateCurrentBombsLengthEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        val count = arg[0] as Int
        val save = arg[1] as Boolean

        JBomb.match.currentLevel.eventHandler.onUpdateBombsLengthEvent(count, save)
        UpdateInfoEventForwarder().invoke(JBomb.match.player!!.toEntityNetwork())
    }
}