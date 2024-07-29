package com.diberardino.jbomb.domain.events.game

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.GameEvent
import com.diberardino.jbomb.network.events.forward.UpdateInfoEventForwarder


class UpdateCurrentAvailableItemsEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        val player = JBomb.match.player ?: return
        val value = arg[0] as Int

        if (value > player.state.maxBombs) {
            return
        }

        JBomb.match.currentLevel.eventHandler.onUpdateCurrentAvailableBombsEvent(value)
        UpdateInfoEventForwarder().invoke(player.toEntityNetwork())
    }
}