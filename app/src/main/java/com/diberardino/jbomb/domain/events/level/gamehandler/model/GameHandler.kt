package com.diberardino.jbomb.domain.events.level.gamehandler.model

import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.animal.AnimalEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy

abstract class GameHandler(protected val level: Level) {
    open fun generate() {
        generateStone()
        generatePlayer()
        startLevel()
    }

    fun startLevel() {
        generateDestroyableBlock()
        spawnMysteryBox()
        spawnBoss()
        spawnEnemies()
        spawnAnimals()
        level.onStartLevel()
    }

    abstract fun generateStone()
    abstract fun generatePlayer()
    abstract fun generateDestroyableBlock()
    abstract fun spawnBoss()
    abstract fun spawnEnemies()
    abstract fun spawnEnemies(availableEnemies: Array<Class<out Enemy>>, count: Int)
    abstract val borderImages: Array<String?>
    abstract fun spawnMysteryBox()
    abstract fun canGameBeEnded(): Boolean
    abstract fun spawnAnimals(availableAnimals: Array<Class<out AnimalEntity>>)
    abstract fun spawnAnimals()
}