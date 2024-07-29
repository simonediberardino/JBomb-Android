package com.diberardino.jbomb.domain.events.game

import com.diberardino.jbomb.domain.events.models.GameEvent

class NewToastGameEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        /*JBomb.JBombFrame.parentPanel.repaint()
        if (arg[0] as Boolean) {
            AudioManager.instance.play(SoundModel.BONUS_ALERT)
        }*/
    }
}