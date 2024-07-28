package com.diberardino.jbomb.network.events

import com.diberardino.jbomb.domain.events.models.HttpEvent

class FireEventForwarder: HttpEvent {
    override fun invoke(vararg extras: Any) {
        //val firingEnemy = (extras[0] as CharacterNetwork)
        //val direction = firingEnemy.direction
        //HttpMessageDispatcher.instance.dispatch(FiringHttpMessage(firingEnemy, direction), private = false)
    }
}