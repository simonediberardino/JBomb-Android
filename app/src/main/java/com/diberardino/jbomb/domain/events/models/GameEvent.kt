package com.diberardino.jbomb.domain.events.models

interface GameEvent {
    fun invoke(vararg arg: Any?) {}
}