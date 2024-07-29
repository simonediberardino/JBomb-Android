package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.graphics

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.remote_player.RemotePlayer
import com.diberardino.jbomb.utils.file_system.Paths

class RemotePlayerImageModel(
        entity: RemotePlayer
) : CharacterImageModel(
        entity = entity
) {
    override fun characterOrientedImages(): Array<String> {
        entity as RemotePlayer

        val entitiesAssetsPath = "${Paths.entitiesFolder}/player/skin${entity.properties.skinId}"

        return Array(4) { index ->
            "$entitiesAssetsPath/player_${entity.state.imageDirection.toString().lowercase()}_${index}.png"
        }
    }
}