package com.diberardino.jbomb.network.callbacks

import com.diberardino.jbomb.network.sockets.TCPServer
import com.diberardino.jbomb.network.gamehandler.OnlineGameHandler

interface TCPServerCallback : OnlineGameHandler {
    fun onCloseServer()
    fun onStartServer()
    fun onClientConnected(indexedClient: TCPServer.IndexedClient)
    fun onClientDisconnected(indexedClient: TCPServer.IndexedClient)
}