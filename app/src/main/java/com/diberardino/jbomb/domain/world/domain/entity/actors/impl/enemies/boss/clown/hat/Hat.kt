package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.hat

import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.orb.Orb
import com.diberardino.jbomb.domain.world.domain.geo.EnhancedDirection
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.ICharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.Clown
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.hat.logic.HatEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.hat.properties.HatEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.orb.properties.OrbEntityProperties
import com.diberardino.jbomb.utils.file_system.Paths

open class Hat : Orb {
    constructor(coordinates: Coordinates?, enhancedDirection: EnhancedDirection?) : super(coordinates, enhancedDirection) {}

    constructor() : this(null, null)

    constructor(id: Long) : super(id)

    override val logic: HatEntityLogic = HatEntityLogic(entity = this)
    override val properties: OrbEntityProperties = OrbEntityProperties(types = EntityTypes.Hat)
    override val graphicsBehavior: ICharacterGraphicsBehavior = CharacterGraphicsBehavior(entity = this)
    override val state: HatEntityState = HatEntityState(entity = this)
    
    override val image: CharacterImageModel = object : CharacterImageModel(
            entity = this,
            entitiesAssetsPath = "${Paths.enemiesFolder}/clown/hat") {
        override fun characterOrientedImages(): Array<String> {
            return Array(10) { index ->
                "${entitiesAssetsPath}${index + 1}.png"
            }
        }
    }

    internal object DEFAULT {
        val INTERACTION_ENTITIES: MutableSet<Class<out Entity>>
            get() = mutableSetOf(BomberEntity::class.java, Clown::class.java)

        val OBSTACLES: MutableSet<Class<out Entity>>
            get() {
                return mutableSetOf(Clown::class.java)
            }

        val SIZE = Orb.DEFAULT.SIZE * 3
        const val MAX_HP = 300
    }
}
