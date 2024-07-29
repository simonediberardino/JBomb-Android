package com.diberardino.jbomb.domain.world.domain.items

import com.diberardino.jbomb.domain.world.domain.entity.items.BombItem
import com.diberardino.jbomb.domain.world.domain.entity.items.PistolItem

class ItemsFactory {
    fun toItem(itemsTypes: ItemsTypes): UsableItem {
        return when (itemsTypes) {
            ItemsTypes.BombItem -> BombItem()
            ItemsTypes.PistolItem -> PistolItem()
        }
    }

    companion object {
        val instance: ItemsFactory by lazy { ItemsFactory() }
    }
}