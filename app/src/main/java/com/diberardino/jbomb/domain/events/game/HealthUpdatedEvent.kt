package com.diberardino.jbomb.domain.events.game

import android.util.Log
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.GameEvent

class HealthUpdatedEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        try {
            val playerHp = JBomb.match.player?.state?.hp ?: 0
            Log.e("HealthUpdatedEvent", playerHp.toString())
            //JBomb.match.inventoryElementControllerHp.setNumItems(playerHp)
        } catch (ignored: UninitializedPropertyAccessException) {
        }
    }
}