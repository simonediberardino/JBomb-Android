package com.diberardino.jbomb.network.models

interface HttpMessage {
    fun serialize() : MutableMap<String, String>
    val senders: Array<HttpActor>
}