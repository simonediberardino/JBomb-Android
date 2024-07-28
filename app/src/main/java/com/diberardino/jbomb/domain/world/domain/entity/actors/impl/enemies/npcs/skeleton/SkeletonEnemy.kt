package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.skeleton

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.firing_enemy.FiringEnemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.firing_enemy.logic.FiringEnemyLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.skeleton.state.SkeletonEnemyState
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.utility.Paths.enemiesFolder
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties.CharacterEntityProperties
import game.domain.world.types.EntityTypes

class SkeletonEnemy : FiringEnemy {
    constructor() : super()
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val logic: FiringEnemyLogic = FiringEnemyLogic(entity = this)
    override val state: SkeletonEnemyState = object: SkeletonEnemyState(entity = this) {}

    override val properties: CharacterEntityProperties = CharacterEntityProperties(types = EntityTypes.Skeleton)

    override val graphicsBehavior: ICharacterGraphicsBehavior = CharacterGraphicsBehavior(entity = this)
    override val image: CharacterImageModel = object : CharacterImageModel(entity = this) {
        override fun characterOrientedImages(): Array<String> =
                Array(4) { index ->
                    "${enemiesFolder}/skeleton/skeleton_${state.imageDirection.toString().lowercase()}_$index.png"
                }
    }

    internal object DEFAULT {
        const val SPEED = 0.7f
    }
}