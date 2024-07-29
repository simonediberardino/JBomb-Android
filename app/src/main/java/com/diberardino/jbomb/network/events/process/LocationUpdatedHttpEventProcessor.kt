package com.diberardino.jbomb.network.events.process

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.mappers.dtoToEntityNetwork
import com.diberardino.jbomb.network.dispatch.HttpMessageDispatcher
import com.diberardino.jbomb.network.messages.LocationHttpMessage
import com.diberardino.jbomb.utils.dev.Extensions.getOrTrim
import com.diberardino.jbomb.utils.dev.Log

class LocationUpdatedHttpEventProcessor : HttpEvent {
    override fun invoke(vararg extras: Any) {
        val info = extras[0] as Map<String, String>
        val entityId = info.getOrTrim("entityId")?.toLong() ?: return
        val locationString = info.getOrTrim("location") ?: return
        val direction = info.getOrTrim("direction")?.toInt() ?: return
        val locTokens = locationString.split(" ").map { it.toInt() }
        val location = Coordinates(locTokens[0], locTokens[1]).fromAbsolute()
        val sentByClient = info.getOrTrim("sentByClient")?.toBoolean() ?: false // if true, the message is sent by a client and must be forwarded to all the other clients

        Log.i(this.javaClass.simpleName, "LocationUpdatedHttpEventProcessor received $entityId, $locationString")

        val entity: Character = JBomb.match.getEntityById(entityId) as Character? ?: return
        entity.info.position = location
        entity.logic.updateMovementDirection(Direction.values()[direction])

        if (sentByClient) { // if message is sent by client, the message must be forwarded to all the other clients
            val newMap = info.toMutableMap()
            newMap["sentByClient"] = false.toString()

            HttpMessageDispatcher.instance.dispatch(
                    httpMessage = LocationHttpMessage(entity.dtoToEntityNetwork()),
                    receiverId = entityId,
                    ignore = true
            )
        }
    }
}