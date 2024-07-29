package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.animal

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.ai.AiEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.ai.logic.AiLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.animal.state.AnimalEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.DestroyableBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.hard_block.HardBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.AbstractExplosion
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.pickups.portals.imp.world_base.WorldPortal

abstract class AnimalEntity: AiEntity {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    constructor() : this(null) {
        if (JBomb.match.player != null) {
            info.position = Coordinates.randomCoordinatesFromPlayer(Character.DEFAULT.SIZE)
        }
    }

    internal object DEFAULT {
        val INTERACTION_ENTITIES: MutableSet<Class<out Entity>> = hashSetOf(AbstractExplosion::class.java)
        val OBSTACLES = mutableSetOf(
                Bomb::class.java,
                Character::class.java,
                HardBlock::class.java,
                DestroyableBlock::class.java,
                WorldPortal::class.java
        )
    }

    override val logic: AiLogic = object: AiLogic(entity = this) {
        override fun onCollision(e: Entity) {
            super.onTalk(e)

            if (e is BomberEntity && state.freezeOnCollideWithPlayer) {
                state.canMove = false
                graphicsBehavior.resetGraphics(entity)
            }
        }

        override fun onExitCollision(e: Entity) {
            super.onExitCollision(e)

            if (e is BomberEntity && state.freezeOnCollideWithPlayer) {
                state.canMove = true
            }
        }
    }

    override val state: AnimalEntityState = AnimalEntityState(entity = this)
    override val graphicsBehavior: ICharacterGraphicsBehavior = CharacterGraphicsBehavior(entity = this)
}