package com.diberardino.jbomb.network.events.process

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.remote_player.RemotePlayer
import com.diberardino.jbomb.network.events.forward.SpawnEntityEventForwarder
import com.diberardino.jbomb.utils.dev.Extensions.getOrTrim
import com.diberardino.jbomb.utils.dev.Log

class PlayerConnectedHttpEventProcessor : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as Map<String, String>

        val clientId = info.getOrTrim("id")?.toLong() ?: return

        Log.i(this.javaClass.simpleName, "PlayerConnectedHttpEventProcessor: $clientId")

        val match = JBomb.match
        val coordinates = match.currentLevel.info.playerSpawnCoordinates

        JBomb.match.resumeIfPaused()

        match.getEntities().forEach { e ->
            Log.i(this.javaClass.simpleName, "Sending entity $e to $clientId")
            SpawnEntityEventForwarder(clientId).invoke(e.toEntityNetwork())
        }

        val skinId = info.getOrTrim("skinId")
        val player = RemotePlayer(coordinates, clientId, skinId?.toInt() ?: 0)
        player.updateInfo(info)
        player.logic.spawn()
    }
}