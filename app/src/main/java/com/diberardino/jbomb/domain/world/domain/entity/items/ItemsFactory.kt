package com.diberardino.jbomb.domain.world.domain.items

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