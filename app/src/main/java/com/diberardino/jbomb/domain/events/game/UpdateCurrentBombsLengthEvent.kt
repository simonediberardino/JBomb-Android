package com.diberardino.jbomb.domain.events.game

import game.domain.events.models.GameEvent

class UpdateCurrentBombsLengthEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        /*val count = arg[0] as Int
        val save = arg[1] as Boolean

        JBomb.match.currentLevel.eventHandler.onUpdateBombsLengthEvent(count, save)
        UpdateInfoEventForwarder().invoke(JBomb.match.player!!.toEntityNetwork())*/
    }
}