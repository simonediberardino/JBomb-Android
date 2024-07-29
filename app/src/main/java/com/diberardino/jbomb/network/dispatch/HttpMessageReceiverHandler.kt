package com.diberardino.jbomb.network.dispatch

import com.diberardino.jbomb.network.events.process.*
import com.diberardino.jbomb.network.models.HttpMessageTypes
import com.diberardino.jbomb.utils.dev.Extensions.getOrTrim
import com.diberardino.jbomb.utils.dev.Log

class HttpMessageReceiverHandler private constructor() {
    // Handles the behavior of each http message;
    fun handle(map: Map<String, String>) {
        val messageTypeInt = map.getOrTrim("messageType")?.toInt() ?: -1

        Log.i(this.javaClass.simpleName, "handling $map")

        val httpEvent = when (HttpMessageTypes.values()[messageTypeInt]) {
            HttpMessageTypes.PLAYER_JOIN_REQUEST -> PlayerConnectedHttpEventProcessor()
            HttpMessageTypes.LEVEL_INFO -> LevelInfoHttpEventProcessor()
            HttpMessageTypes.SPAWNED_ENTITY -> SpawnedEntityHttpEventProcessor()
            HttpMessageTypes.DESPAWNED_ENTITY -> DespawnedEntityHttpEventProcessor()
            HttpMessageTypes.ENTITY_ATTACKED -> AttackEntityEventProcessor()
            HttpMessageTypes.LOCATION -> LocationUpdatedHttpEventProcessor()
            HttpMessageTypes.USE_ITEM -> UseItemHttpEventProcessor()
            HttpMessageTypes.UPDATE_INFO -> UpdateInfoEventProcessor()
            HttpMessageTypes.ENEMIES_COUNT -> UpdateEnemiesCountEventProcessor()
            HttpMessageTypes.ENTITY_COLLIDED -> CollideEventProcessor()
            HttpMessageTypes.BOMB_EXPLODED -> BombExplodedEventProcessor()
            HttpMessageTypes.FIRE -> FireEventProcessor()
            else -> null
        }

        httpEvent?.invoke(map)
    }

    companion object {
        val instance: HttpMessageReceiverHandler by lazy { HttpMessageReceiverHandler() }
    }
}