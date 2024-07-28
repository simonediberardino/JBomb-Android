package com.diberardino.jbomb.misc

interface RunnablePar {
    fun <T> execute(par: T): Any?
}