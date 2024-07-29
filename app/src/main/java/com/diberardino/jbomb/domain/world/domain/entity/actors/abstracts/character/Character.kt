package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character

import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.audio.SoundModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.values.DrawPriority
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.logic.ICharacterEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties.CharacterEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties.CharacterEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.moving_entity.MovingEntity
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.mappers.dtoToEntityNetwork
import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.presentation.ui.panels.game.PitchPanel
import com.diberardino.jbomb.utils.dev.Extensions.getOrTrim
import com.diberardino.jbomb.utils.dev.Log

/**
 * Represents a character in the game, which can move and interact with the environment.
 */
abstract class Character : MovingEntity {
    abstract override val logic: ICharacterEntityLogic
    abstract override val state: CharacterEntityState
    abstract override val graphicsBehavior: ICharacterGraphicsBehavior
    abstract override val properties: CharacterEntityProperties
    abstract override val image: CharacterImageModel

    /**
     * Returns an array of file names for the left-facing icons for this character.
     *
     * @return an array of file names for the left-facing icons
     */
    constructor(coordinates: Coordinates?) : super(coordinates)
    constructor(id: Long) : super(id)
    constructor() : super()

    override fun toEntityNetwork(): EntityNetwork {
        return dtoToEntityNetwork()
    }

    override fun updateInfo(info: Map<String, String>) {
        super.updateInfo(info)

        Log.e(this.javaClass.simpleName, "Updating info ${this} $info")

        val name = info.getOrTrim("name")
        name?.let {
            properties.name = it
        }
    }

    companion object {
        val size = PitchPanel.PIXEL_UNIT * 4 * 2
    }

    internal object DEFAULT {
        val LAST_DIRECTION_UPDATE = 0L
        val PREVIOUS_DIRECTION: Direction? = null
        val CAN_MOVE = true
        val MAX_HP = 100
        val SPEED = 1f
        val STEP_SOUND = null
        val IMAGE_DIRECTIONS: List<Direction>
            get() = Direction.values().asList()
        val DEATH_SOUND: SoundModel
            get() = SoundModel.ENTITY_DEATH
        val SIZE = DEFAULT_SIZE
        val DRAW_PRIORITY: DrawPriority
            get() = DrawPriority.DRAW_PRIORITY_2
        val HITBOX_SIZE_TO_HEIGHT_RATIO: Float = 0.733f
        val STEP_SIZE: Int
            get() = PitchPanel.PIXEL_UNIT
    }
}
