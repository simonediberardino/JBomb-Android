package com.diberardino.jbomb.mappers

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.base.PlaceableEntity
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.network.entity.BomberEntityNetwork
import com.diberardino.jbomb.network.entity.CharacterNetwork
import com.diberardino.jbomb.network.entity.EntityNetwork
import com.diberardino.jbomb.network.entity.PlaceableEntityNetwork

fun EntityTypes.toEntity(id: Long, extra:  Map<String, String>? = null) : Entity? {
    return EntityFactory.instance.toEntity(this, id, extra)
}

fun Entity.dtoToEntityNetwork(): EntityNetwork {
    return EntityNetwork(
            entityId = info.id,
            entityLocation = info.position.toAbsolute(),
            entityType = info.type.ordinal,
            isImmune = state.isImmune
    )
}

fun BomberEntity.dtoToEntityNetwork(): BomberEntityNetwork {
    return BomberEntityNetwork(
            entityId = info.id,
            entityLocation = info.position.toAbsolute(),
            entityType = info.type.ordinal,
            direction = state.direction.ordinal,
            currExplosionLength = state.currExplosionLength,
            currentBombs = state.currentBombs,
            skinId = properties.skinId,
            isImmune = state.isImmune,
            name = properties.name,
            hp = state.hp
    )
}

fun Character.dtoToEntityNetwork(): CharacterNetwork {
    return CharacterNetwork(
            entityId = info.id,
            entityLocation = info.position.toAbsolute(),
            entityType = info.type.ordinal,
            isImmune = state.isImmune,
            direction = state.direction.ordinal,
            name = properties.name
    )
}

fun PlaceableEntity.dtoToEntityNetwork(): PlaceableEntityNetwork {
    return PlaceableEntityNetwork(
            entityId = info.id,
            entityLocation = info.position.toAbsolute(),
            entityType = info.type.ordinal,
            callerId = state.caller?.info?.id ?: -1,
            isImmune = state.isImmune
    )
}