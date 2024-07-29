package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.animals

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.animal.AnimalEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.animal.state.AnimalEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.properties.CharacterEntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.utility.Paths

class FoxAnimal : AnimalEntity {
    constructor() : super()
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val image: CharacterImageModel = object : CharacterImageModel(
            entity = this,
            entitiesAssetsPath = "${Paths.animalsFolder}/fox",
    ) {
        override fun characterOrientedImages(): Array<String> = Array(3) { index ->
            "$entitiesAssetsPath/fox_${state.imageDirection.toString().lowercase()}_$index.png"
        }
    }

    override val state: AnimalEntityState = AnimalEntityState(
            entity = this,
            speed = 0.7f
    )

    override val properties: CharacterEntityProperties = CharacterEntityProperties(
            types = EntityTypes.Fox,
            imageDirections = listOf(Direction.LEFT, Direction.RIGHT)
    )
}