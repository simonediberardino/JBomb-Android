package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.state

import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.properties.BomberEntityState
import game.input.Command

class PlayerState(
        entity: BomberEntity,
) : BomberEntityState(entity = entity
) {
    var maxBombsSaved: Int = 0
    var bombsLengthSaved: Int = 0
    val commandQueue: MutableSet<Command> = mutableSetOf()
}