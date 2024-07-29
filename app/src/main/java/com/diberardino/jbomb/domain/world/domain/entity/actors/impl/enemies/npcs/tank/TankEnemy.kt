package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.tank

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.firing_enemy.FiringEnemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.firing_enemy.logic.FiringEnemyLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.firing_enemy.state.FiringEnemyState
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.utility.Paths.enemiesFolder
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties.CharacterEntityProperties
import com.diberardino.jbomb.domain.world.types.EntityTypes

class TankEnemy : FiringEnemy {
    constructor() : super()
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val logic: FiringEnemyLogic = FiringEnemyLogic(entity = this)
    override val state: FiringEnemyState = object : FiringEnemyState(entity = this) {
        override val shootingChance: Int
            get() = 1
    }

    override val properties: CharacterEntityProperties = CharacterEntityProperties(types = EntityTypes.TankEnemy)

    override val graphicsBehavior: ICharacterGraphicsBehavior = CharacterGraphicsBehavior(entity = this)
    override val image: CharacterImageModel = object : CharacterImageModel(entity = this) {
        override fun characterOrientedImages(): Array<String> =
                arrayOf("$enemiesFolder/tank/tank_${state.imageDirection.toString().lowercase()}.png")
    }
}