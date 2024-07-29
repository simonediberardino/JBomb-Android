package com.diberardino.jbomb.domain.events.level.gamehandler.imp

import android.graphics.Bitmap
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.JBombApplication
import com.diberardino.jbomb.domain.events.level.behavior.DespawnDestroyableBlocksBehavior
import com.diberardino.jbomb.domain.events.level.behavior.GenerateDestroyableBlocksBehavior
import com.diberardino.jbomb.domain.events.level.behavior.GeneratePlayerBehavior
import com.diberardino.jbomb.domain.events.level.behavior.GenerateStoneBehavior
import com.diberardino.jbomb.domain.events.level.behavior.SpawnAnimalsBehavior
import com.diberardino.jbomb.domain.events.level.behavior.SpawnBossBehavior
import com.diberardino.jbomb.domain.events.level.behavior.SpawnEnemiesBehavior
import com.diberardino.jbomb.domain.events.level.behavior.SpawnMysteryBoxBehavior
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.level.gamehandler.model.GameHandler
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.animal.AnimalEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.State
import com.diberardino.jbomb.utility.Utility

open class DefaultGameHandler(level: Level) : GameHandler(level) {
    override val borderImages: Array<Bitmap?>
        get() {
            val SIDES = 4
            val pitch = arrayOfNulls<Bitmap>(SIDES)
            for (i in 0 until SIDES) {
                val path = level.fileSystemHandler.getImageForCurrentLevel("border_$i.png")
                pitch[i] = Utility.loadImage(JBombApplication.context, path)
            }
            return pitch
        }

    override fun generateStone() = GenerateStoneBehavior().invoke()

    override fun generatePlayer() = GeneratePlayerBehavior(level.info.playerSpawnCoordinates).invoke()

    override fun spawnMysteryBox() = SpawnMysteryBoxBehavior(level).invoke()

    override fun generateDestroyableBlock() {
        DespawnDestroyableBlocksBehavior().invoke()
        GenerateDestroyableBlocksBehavior(level).invoke()
    }

    override fun spawnBoss() {
        SpawnBossBehavior(level.info.boss ?: return).invoke()
    }

    override fun spawnEnemies() = spawnEnemies(level.info.availableEnemies, level.info.startEnemiesCount)

    override fun spawnEnemies(availableEnemies: Array<Class<out Enemy>>, count: Int) =
            SpawnEnemiesBehavior(count, availableEnemies).invoke()

    override fun spawnAnimals() = spawnAnimals(level.info.availableAnimals)

    override fun spawnAnimals(availableAnimals: Array<Class<out AnimalEntity>>) =
            SpawnAnimalsBehavior(level.info.startAnimalsCount, availableAnimals).invoke()

    override fun canGameBeEnded(): Boolean = !JBomb.match.getEntities().any { it is BomberEntity && it.state.state != State.DIED }
}