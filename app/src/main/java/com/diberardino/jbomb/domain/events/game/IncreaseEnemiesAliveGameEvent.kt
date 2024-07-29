package com.diberardino.jbomb.domain.events.game

import android.util.Log
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.level.behavior.GameBehavior
import com.diberardino.jbomb.domain.events.models.GameEvent
import com.diberardino.jbomb.network.events.forward.UpdateEnemiesCountEventForwarder

class IncreaseEnemiesAliveGameEvent: GameEvent {
    // Only for server, notifies all clients
    override fun invoke(vararg arg: Any?) {
        val gameBehavior: GameBehavior = object : GameBehavior() {
            override fun hostBehavior(): () -> Unit {
                return {
                    val enemiesAlive = JBomb.match.enemiesAlive

                    UpdateLocalEnemiesCountGameEvent().invoke(enemiesAlive + 1) // updates locally
                    UpdateEnemiesCountEventForwarder().invoke(enemiesAlive + 1) // notifies clients

                    Log.e(this.javaClass.simpleName, "Host is notifying new increased count ${enemiesAlive - 1}")
                }
            }

            override fun clientBehavior(): () -> Unit {
                return {}
            }
        }

        gameBehavior.invoke()
    }
}