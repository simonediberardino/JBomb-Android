package com.diberardino.jbomb.domain.world.domain.items

enum class ItemsTypes {
    BombItem,
    PistolItem;

    fun toInt(): Int = ordinal
    fun toItem() : UsableItem = ItemsFactory.instance.toItem(this)
}