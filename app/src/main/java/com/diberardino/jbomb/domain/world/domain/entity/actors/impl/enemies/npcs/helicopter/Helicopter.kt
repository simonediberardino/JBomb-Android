package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.helicopter

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.flying_enemy.FlyingEnemy
import com.diberardino.jbomb.utility.Paths.enemiesFolder
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties.CharacterEntityProperties
import com.diberardino.jbomb.domain.world.types.EntityTypes

class Helicopter : FlyingEnemy {
    constructor() : super()
    constructor(id: Long) : super(id)

    override val image: CharacterImageModel = object : CharacterImageModel(
            entity = this,
            entitiesAssetsPath = "$enemiesFolder/heli",
    ) {
        override fun characterOrientedImages(): Array<String> {
            return Array(3) { index ->
                "$entitiesAssetsPath/heli_${state.imageDirection.toString().lowercase()}_$index.png"
            }
        }
    }

    override val properties: CharacterEntityProperties = CharacterEntityProperties(types = EntityTypes.Helicopter)
    override val graphicsBehavior: ICharacterGraphicsBehavior = CharacterGraphicsBehavior(entity = this)
}