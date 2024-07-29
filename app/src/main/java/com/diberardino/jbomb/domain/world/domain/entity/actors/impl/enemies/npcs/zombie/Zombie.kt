package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.zombie

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties.CharacterEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ai_enemy.AiEnemy
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.zombie.properties.ZombieState
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.utility.Paths.enemiesFolder

class Zombie : AiEnemy {
    constructor() : super()
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val image: CharacterImageModel = object : CharacterImageModel(
            entity = this,
            entitiesAssetsPath = "$enemiesFolder/zombie"
    ) {
        override fun characterOrientedImages(): Array<String> = Array(4) {
            "$entitiesAssetsPath/zombie_${state.imageDirection.toString().lowercase()}_$it.png"
        }
    }
    
    override val state: ZombieState = ZombieState(entity = this)
    override val graphicsBehavior: ICharacterGraphicsBehavior = CharacterGraphicsBehavior(entity = this)
    override val properties: CharacterEntityProperties = CharacterEntityProperties(
            types = EntityTypes.Zombie
    )

    internal object DEFAULT {
        const val SPEED = 0.5f
        const val MAX_HP = 300
    }
}