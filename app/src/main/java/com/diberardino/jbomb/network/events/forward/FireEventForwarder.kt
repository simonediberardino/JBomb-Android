package com.diberardino.jbomb.network.events.forward

import com.diberardino.jbomb.domain.events.models.HttpEvent
import com.diberardino.jbomb.network.dispatch.HttpMessageDispatcher
import com.diberardino.jbomb.network.entity.CharacterNetwork
import com.diberardino.jbomb.network.messages.FiringHttpMessage

class FireEventForwarder: HttpEvent {
    override fun invoke(vararg extras: Any) {
        val firingEnemy = (extras[0] as CharacterNetwork)
        val direction = firingEnemy.direction
        HttpMessageDispatcher.instance.dispatch(FiringHttpMessage(firingEnemy, direction), private = false)
    }
}