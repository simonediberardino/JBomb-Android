package com.diberardino.jbomb.domain.events.game

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.GameEvent


class ScoreGameEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        JBomb.match.currentLevel!!.eventHandler.onScoreGameEvent(arg[0] as Int)
    }
}