package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bonus.mystery_box.base.state

import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.properties.BlockEntityState
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bonus.mystery_box.base.MysteryBox

abstract class MysteryBoxState(
    entity: Entity,
    size: Int = MysteryBox.DEFAULT.SIZE,
    var level: () -> Level?,
    var buyer: () -> BomberEntity?
) : BlockEntityState(entity = entity,
        size = size
) {
    var status = MysteryBox.Status.CLOSED
    var lastClickInteraction: Long = 0
    abstract val price: Int
}