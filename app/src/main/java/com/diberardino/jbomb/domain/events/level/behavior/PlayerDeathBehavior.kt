package com.diberardino.jbomb.domain.events.level.behavior

class PlayerDeathBehavior : GameBehavior() {
    override fun hostBehavior(): () -> Unit = {
        /*if (JBomb.match.currentLevel.gameHandler.canGameBeEnded() && !JBomb.isGameEnded) {
            val t = Timer(EntityInteractable.SHOW_DEATH_PAGE_DELAY_MS.toInt()) { _: ActionEvent? ->
                if (!JBomb.isGameEnded) {
                    DefeatGameEvent().invoke(null)
                    JBomb.destroyLevel(true)
                    JBomb.showActivity(GameOverPanel::class.java)
                }
            }
            t.isRepeats = false
            t.start()
        }*/
    }

    override fun clientBehavior(): () -> Unit = {}
}