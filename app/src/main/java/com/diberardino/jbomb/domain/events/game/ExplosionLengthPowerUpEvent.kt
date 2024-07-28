package com.diberardino.jbomb.domain.events.game

import game.domain.events.models.GameEvent


class ExplosionLengthPowerUpEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        /*var bomberEntity = arg[0] as BomberEntity
        JBomb.match
                .currentLevel
                .eventHandler
                .onUpdateBombsLengthEvent(
                        bomberEntity.state.currExplosionLength + 1,
                        true
                )*/
    }
}