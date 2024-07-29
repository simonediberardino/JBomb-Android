package com.diberardino.jbomb.domain.events.models

interface RunnablePar {
    fun <T> execute(par: T): Any?
}