package com.diberardino.jbomb.domain.events.game

import game.domain.events.models.GameEvent


class RoundPassedGameEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        //JBomb.match.currentLevel!!.eventHandler.onRoundPassedGameEvent()
    }
}