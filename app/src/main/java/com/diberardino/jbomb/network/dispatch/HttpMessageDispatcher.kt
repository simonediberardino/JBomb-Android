package com.diberardino.jbomb.network.dispatch

import android.util.Log
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.network.gamehandler.ClientGameHandler
import com.diberardino.jbomb.network.models.HttpActor
import com.diberardino.jbomb.network.models.HttpMessage
import com.diberardino.jbomb.network.serializing.HttpParserSerializer

/**
 * A class responsible for dispatching HTTP messages within the Bomberman game.
 */
class HttpMessageDispatcher private constructor() {
    private val actorId: Long
        get() = if (JBomb.match.onlineGameHandler is ClientGameHandler) {
            (JBomb.match.onlineGameHandler as ClientGameHandler).id
        } else {
            JBomb.match.player?.info?.id ?: -1L
        }

    /**
     * Dispatches the given HTTP message to all clients.
     *
     * @param httpMessage The HTTP message to dispatch.
     */
    fun dispatch(httpMessage: HttpMessage, private: Boolean = true) {
        dispatch(httpMessage, -1, false, private = private)
    }

    /**
     * Dispatches the given HTTP message to a specific client based on the receiverId.
     *
     * @param httpMessage The HTTP message to dispatch.
     * @param receiverId The ID of the intended receiver client.
     */
    fun dispatch(httpMessage: HttpMessage, receiverId: Long, private: Boolean = true) {
        dispatch(httpMessage, receiverId, false, private = private)
    }

    /**
     * Dispatches the given HTTP message with options for targeted or broadcast delivery.
     *
     * @param httpMessage The HTTP message to dispatch.
     * @param receiverId The ID of the intended receiver client.
     * @param ignore If true, the message will be sent to all clients except the specified receiverId.
     */
    fun dispatch(
            httpMessage: HttpMessage,
            receiverId: Long,
            ignore: Boolean,
            private: Boolean = false
    ) {
        val data: String = HttpParserSerializer.instance.serialize(httpMessage, private, actorId)

        Log.i(this.javaClass.simpleName, "HttpMessageDispatcher: $httpMessage, $receiverId, $ignore")

        for (sender in httpMessage.senders) {
            if (dispatch(data, sender, receiverId, ignore))
                break
        }
    }

    /**
     * Dispatches the serialized data to the specified HttpActor based on the Bomberman game context.
     *
     * @param data The serialized data to dispatch.
     * @param httpActor The target HttpActor (SERVER or CLIENT).
     * @param receiverId The ID of the intended receiver client.
     * @param ignore If true, the message will be sent to all clients except the specified receiverId.
     * @return True if the message was successfully dispatched, false otherwise.
     */
    private fun dispatch(data: String, httpActor: HttpActor, receiverId: Long, ignore: Boolean): Boolean {
        Log.i(this.javaClass.simpleName, """
            {
              "message": "dispatch",
              "data": "$data",
              "actor": "$httpActor",
              "server": ${JBomb.match.isServer},
              "receiverId": ${receiverId},
              "ignore:" $ignore,"
              "client": ${JBomb.match.isClient}
            }
        """)

        if (httpActor == HttpActor.SERVER && JBomb.match.isServer) {
            JBomb.match.onlineGameHandler?.sendData(
                    data,
                    receiverId,
                    ignore
            )
            return true
        } else if (httpActor == HttpActor.CLIENT && JBomb.match.isClient) {
            JBomb.match.onlineGameHandler?.sendData(data, receiverId, ignore)
            return true
        }

        return false
    }

    companion object {
        /**
         * Singleton instance of the HttpMessageDispatcher class.
         */
        val instance: HttpMessageDispatcher by lazy { HttpMessageDispatcher() }
    }
}
