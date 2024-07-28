package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.graphics

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.Player
import game.data.data.DataInputOutput
import game.utils.file_system.Paths

class PlayerImageModel(
        entity: Player
) : CharacterImageModel(
        entity = entity,
        entitiesAssetsPath = "${Paths.entitiesFolder}/player/${DataInputOutput.getInstance().skin}"
) {
    override fun characterOrientedImages(): Array<String> {
        entity as Player

        return Array(4) { index ->
            "$entitiesAssetsPath/player_${entity.state.imageDirection.toString().lowercase()}_${index}.png"
        }
    }
}