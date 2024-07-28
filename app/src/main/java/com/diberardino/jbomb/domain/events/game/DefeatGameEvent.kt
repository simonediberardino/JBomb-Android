package com.diberardino.jbomb.domain.events.game

import game.domain.events.models.GameEvent


class DefeatGameEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        //JBomb.match.currentLevel.eventHandler.onDefeatGameEvent()
    }
}