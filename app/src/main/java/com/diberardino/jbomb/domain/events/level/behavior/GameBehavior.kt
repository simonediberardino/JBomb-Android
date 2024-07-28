package com.diberardino.jbomb.domain.events.level.behavior

abstract class GameBehavior {
    abstract fun hostBehavior(): () -> Unit
    abstract fun clientBehavior(): (() -> Unit)

    fun invoke() {
        //if (JBomb.match.isServer) {
        //hostBehavior().invoke()
        //} else if (JBomb.match.isClient) {
        //clientBehavior().invoke()
        //}
    }
}