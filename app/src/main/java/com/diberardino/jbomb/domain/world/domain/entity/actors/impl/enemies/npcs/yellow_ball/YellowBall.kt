package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.yellow_ball

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ai_enemy.AiEnemy
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.utility.Paths.enemiesFolder
import com.diberardino.jbomb.values.Dimensions.DEFAULT_SIZE
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.properties.EnemyEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.yellow_ball.properties.YellowBallProperties
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.domain.world.types.EntityTypes

class YellowBall : AiEnemy {
    constructor() : super()
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val image: CharacterImageModel = object : CharacterImageModel(
            entity = this,
            entitiesAssetsPath = "$enemiesFolder/yellow_ball/yellow_ball"
    ) {
        override fun characterOrientedImages(): Array<String> {
            return Array(4) { index ->
                "$enemiesFolder/yellow_ball/yellow_ball_${state.imageDirection.toString().lowercase()}_$index.png"
            }
        }
    }

    override val state: EnemyEntityState = EnemyEntityState(entity = this, size = DEFAULT.SIZE)
    override val graphicsBehavior: ICharacterGraphicsBehavior = CharacterGraphicsBehavior(entity = this)
    override val properties: YellowBallProperties = YellowBallProperties(types = EntityTypes.YellowBall)

    internal object DEFAULT {
        val IMAGE_DIRECTIONS: List<Direction>
            get() = listOf(Direction.RIGHT, Direction.LEFT)
        val SIZE = DEFAULT_SIZE
    }
}