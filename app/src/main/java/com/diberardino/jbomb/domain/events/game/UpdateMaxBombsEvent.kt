package com.diberardino.jbomb.domain.events.game

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.GameEvent


class UpdateMaxBombsEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        val count = arg[0] as Int
        val save = arg[1] as Boolean
        JBomb.match.currentLevel.eventHandler.onUpdateMaxBombsGameEvent(count, save)
    }
}