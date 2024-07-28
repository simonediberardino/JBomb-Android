package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base

import game.audio.SoundModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.DestroyableBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.hard_block.HardBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.logic.BomberEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.logic.IBomberEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.properties.BomberEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.properties.BomberEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bonus.mystery_box.base.MysteryBox
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.AbstractExplosion
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.Explosive
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import game.mappers.dtoToEntityNetwork
import game.network.entity.EntityNetwork
import game.presentation.ui.panels.game.PitchPanel
import game.utils.dev.Extensions.getOrTrim
import game.utils.dev.Log

abstract class BomberEntity : Character, Explosive {
    constructor() : super()
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val logic: IBomberEntityLogic = BomberEntityLogic(entity = this)
    override val state: BomberEntityState = BomberEntityState(entity = this)
    override val graphicsBehavior: ICharacterGraphicsBehavior = CharacterGraphicsBehavior(entity = this)
    abstract override val properties: BomberEntityProperties

    override fun toEntityNetwork(): EntityNetwork = dtoToEntityNetwork()

    override fun updateInfo(info: Map<String, String>) {
        super.updateInfo(info)

        val currExplosionLength = info.getOrTrim("currExplosionLength")?.toInt()
        val currentBombs = info.getOrTrim("currentBombs")?.toInt()
        val skinId = info.getOrTrim("skinId")?.toInt()
        val hp = info.getOrTrim("hp")?.toInt()

        currExplosionLength?.let { state.currExplosionLength = it }
        currentBombs?.let { state.currentBombs = it }
        skinId?.let { properties.skinId = it }
        hp?.let {
            Log.e("Updating health ${hp} $hp")
            state.hp = it
        }
    }

    init {
        // TODO
        //state.entitiesClassListMouseClick.add(MysteryBoxPerk::class.java)
        //image.hitboxSizeToHeightRatio = 0.733f
    }

    // Defines the set of obstacle entities that block the propagation of explosions.
    override val explosionObstacles: Set<Class<out Entity>> = setOf(
            HardBlock::class.java,
            DestroyableBlock::class.java
    )

    // Defines the set of entities that can interact with explosions.
    override val explosionInteractionEntities: Set<Class<out Entity>> = setOf(
            DestroyableBlock::class.java,
            Enemy::class.java,
            Bomb::class.java
    )

    // Defines the maximum distance an explosion can propagate.
    override val maxExplosionDistance: Int
        get() = state.currExplosionLength

    companion object {
        const val MAX_BOMB_CAN_HOLD = 10
        val SPAWN_OFFSET = Coordinates((PitchPanel.GRID_SIZE - Character.DEFAULT.SIZE) / 2, PitchPanel.GRID_SIZE - size)
    }

    internal object DEFAULT {
        val STEP_SOUND = SoundModel.STEP_SOUND

        val INTERACTION_ENTITIES: MutableSet<Class<out Entity>>
            get() = mutableSetOf(
                    AbstractExplosion::class.java,
                    Enemy::class.java,
                    PowerUp::class.java
            )

        const val MAX_HP = 300
        val MOUSE_CLICK_INTERACTION_ENTITIES: MutableSet<Class<out Entity>>
            get() = mutableSetOf(MysteryBox::class.java)
        val MOUSE_DRAG_INTERACTION_ENTITIES: MutableSet<Class<out Entity>>
            get() = mutableSetOf()
    }
}
