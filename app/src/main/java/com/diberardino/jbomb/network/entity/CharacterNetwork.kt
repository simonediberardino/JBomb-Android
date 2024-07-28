package game.network.entity

import com.diberardino.jbomb.domain.world.domain.geo.Coordinates

open class CharacterNetwork(
        entityId: Long,
        entityLocation: Coordinates?,
        entityType: Int,
        isImmune: Boolean,
        val direction: Int,
        val name: String? = null
) : EntityNetwork(entityId, entityLocation, entityType, isImmune)