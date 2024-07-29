package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.logic

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.logic.EntityLogic
import com.diberardino.jbomb.domain.tasks.observer.Observable2

open class BlockEntityLogic(entity: Entity) : EntityLogic(entity), IBlockEntityLogic {
    override fun destroy() {
        entity.logic.eliminated()
    }

    override fun damageAnimation() {}

    override fun onAttackReceived(damage: Int) {
        destroy()
    }

    override fun observerUpdate(arg: Observable2.ObserverParam) {}

    override fun interact(e: Entity?) {}

    override fun doInteract(e: Entity?) {}
}