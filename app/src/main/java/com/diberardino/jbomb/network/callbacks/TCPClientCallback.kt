package com.diberardino.jbomb.network.callbacks

import com.diberardino.jbomb.network.gamehandler.OnlineGameHandler

interface TCPClientCallback : OnlineGameHandler {
    fun onError(message: String?)
    fun onDisconnect()
    fun onConnect()
    fun onIdReceived(id: Long)
}