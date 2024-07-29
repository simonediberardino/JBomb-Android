package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player

import com.diberardino.jbomb.audio.SoundModel
import com.diberardino.jbomb.domain.events.game.HealthUpdatedEvent
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.logic.IBomberEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.properties.BomberEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.graphics.PlayerImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.logic.PlayerLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.state.PlayerState
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.utility.Extensions.getOrTrim
import com.diberardino.jbomb.utility.SkinUtilities

class Player : BomberEntity {
    constructor() : super()
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val logic: IBomberEntityLogic = PlayerLogic(entity = this)
    override val state: PlayerState = PlayerState(entity = this)
    override val graphicsBehavior: ICharacterGraphicsBehavior = CharacterGraphicsBehavior(entity = this)
    override val image: PlayerImageModel = PlayerImageModel(entity = this)
    override val properties: BomberEntityProperties = object : BomberEntityProperties(
            types = EntityTypes.BomberEntity,
            skinId = SkinUtilities.getSkinId(
                "skin0"
                //DataInputOutput.getInstance().skin
            ),
    ) {
        override var name: String? = "user"//DataInputOutput.getInstance().username
    }

    internal object DEFAULT {
        val DEATH_SOUND: SoundModel
            get() = SoundModel.PLAYER_DEATH
    }

    override fun updateInfo(info: Map<String, String>) {
        super.updateInfo(info)

        val hp = info.getOrTrim("hp")?.toInt()

        hp?.let {
            state.hp = it
            HealthUpdatedEvent().invoke(null)
        }
    }
}