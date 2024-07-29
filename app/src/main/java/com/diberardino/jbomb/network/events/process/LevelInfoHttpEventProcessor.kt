package com.diberardino.jbomb.network.events.process

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.network.dispatch.HttpMessageDispatcher
import com.diberardino.jbomb.network.gamehandler.ClientGameHandler
import com.diberardino.jbomb.network.messages.PlayerJoinRequestHttpMessage
import com.diberardino.jbomb.utils.dev.Extensions.getOrTrim
import com.diberardino.jbomb.utils.dev.Log

class LevelInfoHttpEventProcessor : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as Map<String, String>

        val id = info.getOrTrim("id")?.toIntOrNull() ?: return
        val levelId = info.getOrTrim("levelId")?.toIntOrNull() ?: return
        val worldId = info.getOrTrim("worldId")?.toIntOrNull() ?: return
        val player = JBomb.match.player ?: return

        val onlineGameHandler = JBomb.match.onlineGameHandler as? ClientGameHandler
        onlineGameHandler?.id = id.toLong()

        val levelClassOpt = Level.findLevel(worldId, levelId)

        Log.i(this.javaClass.simpleName, "LevelInfoHttpEventProcessor: $info, $levelClassOpt")

        Thread.sleep(1500) // TODO

        if (levelClassOpt.isPresent) {
            JBomb.startLevel(
                    level = levelClassOpt.get().getConstructor().newInstance(),
                    onlineGameHandler = onlineGameHandler,
                    disconnect = false
            ) {
                // Client confirms joining the match after receiving the ID.
                HttpMessageDispatcher.instance.dispatch(PlayerJoinRequestHttpMessage(id, player.toEntityNetwork()))
            }
        } else {
            throw RuntimeException("Level $worldId, $levelId does not exist")
        }
    }
}