package com.diberardino.jbomb.domain.tasks

import com.diberardino.jbomb.domain.tasks.observer.Observable2
import com.diberardino.jbomb.domain.tasks.observer.Observer2
import com.diberardino.jbomb.utils.time.now
import com.diberardino.jbomb.utils.time.timeunit

abstract class GameTickerObserver : Observer2 {
    var lastUpdate = 0L
        private set

    override fun update(arg: Observable2.ObserverParam) {
        lastUpdate = now()
    }

    companion object {
        val DEFAULT_OBSERVER_UPDATE = timeunit() * 8
    }
}