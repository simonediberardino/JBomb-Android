package com.diberardino.jbomb.domain.tasks

import com.diberardino.jbomb.JBomb
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class GarbageCollectorTask {

    private var job: Job? = null

    fun start() {
        job = JBomb.match.scope.launch {
            while (isActive) {
                delay(DELAY_MS)
                System.gc()
            }
        }
    }

    fun stop() {
        job?.cancel()
    }

    companion object {
        private const val DELAY_MS = 5 * 1000L
    }
}
