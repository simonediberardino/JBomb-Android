package com.diberardino.jbomb.network.events.process

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.domain.events.game.UpdateLocalEnemiesCountGameEvent
import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.utils.dev.Extensions.getOrTrim
import com.diberardino.jbomb.utils.dev.Log

class UpdateEnemiesCountEventProcessor : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as Map<String, String>

        val count = info.getOrTrim("enemiesCount")?.toInt() ?: return

        Log.e(this.javaClass.simpleName, "Client received $count enemies left")

        UpdateLocalEnemiesCountGameEvent().invoke(count)
    }
}