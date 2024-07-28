package com.diberardino.jbomb.domain.events.models

interface HttpEvent {
    fun invoke(vararg extras: Any)
}