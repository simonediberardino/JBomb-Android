package com.diberardino.jbomb.domain.events.level.behavior

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.game.DefeatGameEvent
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.entity_interactable.EntityInteractable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlayerDeathBehavior : GameBehavior() {
    override fun hostBehavior(): () -> Unit = {
        if (JBomb.match.currentLevel.gameHandler.canGameBeEnded() && !JBomb.isGameEnded) {
            JBomb.match.scope.launch {
                delay(EntityInteractable.SHOW_DEATH_PAGE_DELAY_MS.toLong())
                if (!JBomb.isGameEnded) {
                    DefeatGameEvent().invoke(null)
                    JBomb.match.destroy(true)
                    //JBomb.showActivity(GameOverPanel::class.java)
                }
            }
        }
    }


    override fun clientBehavior(): () -> Unit = {}
}