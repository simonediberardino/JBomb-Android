package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.logic

import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.hat.Hat
import com.diberardino.jbomb.domain.world.domain.geo.Direction

interface IClownLogic {
    fun spawnOrbs()
    fun spawnEnhancedOrbs()
    fun calculateExplosionOffsets(d: Direction): IntArray
    fun spawnExplosion()
    fun throwHat()
    fun checkAndSpawnExplosion()
    fun checkAndSpawnOrbs()
    fun checkAndThrowHat()
    fun pickupHat(hat: Hat)
}