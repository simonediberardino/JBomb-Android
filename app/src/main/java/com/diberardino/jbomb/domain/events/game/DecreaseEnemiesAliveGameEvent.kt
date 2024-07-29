package com.diberardino.jbomb.domain.events.game

import com.diberardino.jbomb.domain.events.models.GameEvent

class DecreaseEnemiesAliveGameEvent : GameEvent {
    // Only for server, notifies all clients
    override fun invoke(vararg arg: Any?) {
        /*val gameBehavior: GameBehavior = object : GameBehavior() {
            override fun hostBehavior(): () -> Unit {
                return {
                    val enemiesAlive = JBomb.match.enemiesAlive

                    UpdateLocalEnemiesCountGameEvent().invoke(enemiesAlive - 1) // updates locally
                    UpdateEnemiesCountEventForwarder().invoke(enemiesAlive - 1) // notifies clients

                    Log.e(this.javaClass.simpleName, "Host is notifying new decreased count ${enemiesAlive - 1}")
                }
            }

            override fun clientBehavior(): () -> Unit {
                return {}
            }
        }

        gameBehavior.invoke()*/
    }
}