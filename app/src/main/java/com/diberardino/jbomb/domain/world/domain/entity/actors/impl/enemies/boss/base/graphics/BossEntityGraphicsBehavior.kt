package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.graphics

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics.CharacterGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.base.Boss

open class BossEntityGraphicsBehavior(override val entity: Boss) : CharacterGraphicsBehavior(entity = entity),
    IBossEntityGraphicsBehavior {
    override fun getImageFromRageStatus(): String {
        return entity.image.imagePath
    }
}