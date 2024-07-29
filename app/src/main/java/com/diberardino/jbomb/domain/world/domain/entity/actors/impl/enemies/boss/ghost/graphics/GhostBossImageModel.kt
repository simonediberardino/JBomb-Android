package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.ghost.graphics

import com.diberardino.jbomb.domain.events.models.RunnablePar
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.ghost.GhostBoss

class GhostBossImageModel(entity: Entity) : CharacterImageModel(entity = entity) {
    override var hitboxSizeToHeightRatio: Float = GhostBoss.DEFAULT.HITBOX_SIZE_TO_HEIGHT_RATIO
    override var hitboxSizeToWidthRatio: Float = GhostBoss.DEFAULT.HITBOX_SIZE_TO_WIDTH_RATIO

    override var paddingTopFunction: RunnablePar = object : RunnablePar {
        override fun <T> execute(par: T): Any {
            entity.image.paddingTop = (0)
            return 0
        }
    }

    override fun characterOrientedImages(): Array<String> {
        entity as GhostBoss
        return arrayOf(entity.graphicsBehavior.getImageFromRageStatus())
    }
}