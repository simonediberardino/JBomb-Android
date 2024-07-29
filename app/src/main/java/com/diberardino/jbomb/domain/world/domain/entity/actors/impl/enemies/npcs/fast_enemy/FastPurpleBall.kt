package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.fast_enemy

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.utility.Paths.enemiesFolder
import com.diberardino.jbomb.values.Dimensions.DEFAULT_SIZE
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.properties.EnemyEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ai_enemy.AiEnemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.fast_enemy.properties.FastEnemyProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.fast_enemy.properties.FastEnemyState
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.domain.world.types.EntityTypes

/**
 * An enemy with increased speed multiplier;
 */
class FastPurpleBall : AiEnemy {
    constructor() : super()
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val image: CharacterImageModel = object : CharacterImageModel(
            entity = this,
            entitiesAssetsPath = "$enemiesFolder/fast_enemy/fast_enemy"
    ) {
        override var hitboxSizeToHeightRatio = FastPurpleBall.DEFAULT.HITBOX_SIZE_TO_HEIGHT_RATIO

        override fun characterOrientedImages(): Array<String> = Array(4) { index ->
            "${entitiesAssetsPath}_${state.imageDirection.toString().lowercase()}_$index.png"
        }
    }

    override val properties: FastEnemyProperties = FastEnemyProperties(types = EntityTypes.FastEnemy)
    override val state: EnemyEntityState = FastEnemyState(entity = this)
    override val graphicsBehavior: ICharacterGraphicsBehavior = CharacterGraphicsBehavior(entity = this)

    internal object DEFAULT {
        val SPEED = 1.5f
        val SIZE = DEFAULT_SIZE
        const val HITBOX_SIZE_TO_HEIGHT_RATIO = 0.527f
        val IMAGE_DIRECTIONS: List<Direction>
            get() = listOf(Direction.RIGHT, Direction.LEFT)
    }
}