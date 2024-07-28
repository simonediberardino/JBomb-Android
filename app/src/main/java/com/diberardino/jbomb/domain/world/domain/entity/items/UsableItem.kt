package com.diberardino.jbomb.domain.world.domain.items

import game.JBomb
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity

abstract class UsableItem {
    lateinit var owner: BomberEntity
    abstract fun use(itemId: Long? = null): Long
    abstract fun combineItems(item: UsableItem)
    abstract val imagePath: String?
    abstract val count: Int
    abstract val type: ItemsTypes

    fun give() {
        JBomb.match.give(owner, this)
    }

    open fun remove() {
        JBomb.match.removeItem(owner)
    }
}