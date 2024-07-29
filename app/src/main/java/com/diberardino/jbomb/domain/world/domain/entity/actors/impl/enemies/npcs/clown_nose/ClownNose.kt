package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.clown_nose

import com.diberardino.jbomb.audio.SoundModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.orb.Orb
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.orb.logic.OrbEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.orb.properties.OrbEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.domain.world.domain.geo.EnhancedDirection
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.utility.Paths.enemiesFolder

class ClownNose : Orb {
    constructor(coordinates: Coordinates?, enhancedDirection: EnhancedDirection?) : super(coordinates, enhancedDirection)
    constructor(coordinates: Coordinates?, direction: Direction?) : super(coordinates, direction)
    constructor() : this(null, null as Direction?)
    constructor(id: Long) : super(id)

    

    override val graphicsBehavior: ICharacterGraphicsBehavior = CharacterGraphicsBehavior(entity = this)

    override val image: CharacterImageModel = object : CharacterImageModel(
            entity = this,
            entitiesAssetsPath = "$enemiesFolder/clown/clown_orb.png"
    ) {
        override fun characterOrientedImages(): Array<String> = arrayOf(entitiesAssetsPath)
    }

    override val logic: OrbEntityLogic = object: OrbEntityLogic(entity = this) {
        override fun doInteract(e: Entity?) {
            super.doInteract(e)

            if (isObstacle(e)) {
                attack(entity)
            }
        }
    }

    override val properties: OrbEntityProperties = OrbEntityProperties(
            types = EntityTypes.ClownNose,
            deathSound = SoundModel.CLOWN_NOSE_DEATH
    )
}