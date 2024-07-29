package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.ai

import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.ai.logic.AiLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character

abstract class AiEntity : Character {
    constructor() : super()
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val logic: AiLogic = AiLogic(entity = this)
}