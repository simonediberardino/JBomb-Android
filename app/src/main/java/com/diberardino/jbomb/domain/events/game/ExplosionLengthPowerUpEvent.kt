package com.diberardino.jbomb.domain.events.game

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.models.GameEvent
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity


class ExplosionLengthPowerUpEvent : GameEvent {
    override fun invoke(vararg arg: Any?) {
        var bomberEntity = arg[0] as BomberEntity
        JBomb.match
                .currentLevel
                .eventHandler
                .onUpdateBombsLengthEvent(
                        bomberEntity.state.currExplosionLength + 1,
                        true
                )
    }
}