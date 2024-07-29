package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ai_enemy.logic

import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction

interface IAiLogic {
    fun chooseDirection(forceChange: Boolean): Direction?
    fun changeDirection()
    fun process()
}