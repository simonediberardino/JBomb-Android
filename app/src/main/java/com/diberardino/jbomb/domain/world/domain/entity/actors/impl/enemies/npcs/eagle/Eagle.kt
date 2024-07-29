package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.eagle

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.utility.Paths.enemiesFolder
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.orb.properties.OrbEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.flying_enemy.FlyingEnemy
import com.diberardino.jbomb.domain.world.types.EntityTypes

class Eagle : FlyingEnemy {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)
    constructor() : super()

    

    override val graphicsBehavior: ICharacterGraphicsBehavior = CharacterGraphicsBehavior(entity = this)

    override val image: CharacterImageModel = object : CharacterImageModel(
            entity = this,
            entitiesAssetsPath = "$enemiesFolder/eagle"
    ) {
        override fun characterOrientedImages(): Array<String> = Array(3) { index ->
            "$entitiesAssetsPath/eagle_${state.imageDirection.toString().lowercase()}_$index.png"
        }
    }

    override val properties: OrbEntityProperties = OrbEntityProperties(
            types = EntityTypes.Eagle
    )
}