package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ghost_enemy

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ai_enemy.AiEnemy
import com.diberardino.jbomb.utility.Paths.enemiesFolder
import com.diberardino.jbomb.values.Dimensions.DEFAULT_SIZE
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.DestroyableBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ghost_enemy.properties.GhostEnemyProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ghost_enemy.properties.GhostEnemyState
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.domain.world.types.EntityTypes

class GhostEnemy : AiEnemy {
    constructor() : super()
    constructor(id: Long) : super(id)

    override val image: CharacterImageModel = object : CharacterImageModel(
            entity = this
    ) {
        override var hitboxSizeToHeightRatio = DEFAULT.HITBOX_SIZE_TO_HEIGHT_RATIO
        override var hitboxSizeToWidthRatio = DEFAULT.HITBOX_SIZE_TO_WIDTH_RATIO

        override fun characterOrientedImages(): Array<String> {
            return arrayOf("$enemiesFolder/mini_ghost/ghost_${state.imageDirection.toString().lowercase()}.png")
        }
    }
    
    override val properties: GhostEnemyProperties = GhostEnemyProperties(types = EntityTypes.GhostEnemy)
    override val graphicsBehavior: ICharacterGraphicsBehavior = CharacterGraphicsBehavior(entity = this)
    override val state: GhostEnemyState = GhostEnemyState(entity = this)

    internal object DEFAULT {
        val WHITELIST_OBSTACLES: MutableSet<Class<out Entity>>
            get() = mutableSetOf(DestroyableBlock::class.java)
        val SIZE = DEFAULT_SIZE
        val IMAGE_DIRECTIONS: List<Direction>
            get() = listOf(Direction.RIGHT, Direction.LEFT)
        val HITBOX_SIZE_TO_WIDTH_RATIO = 0.837f
        val HITBOX_SIZE_TO_HEIGHT_RATIO = 1f
    }
}