package game.domain.level.gamehandler.imp

import com.diberardino.jbomb.domain.events.level.behavior.GenerateStoneBehavior
import game.JBomb
import com.diberardino.jbomb.domain.events.level.behavior.*
import game.domain.level.gamehandler.model.GameHandler
import game.domain.level.levels.Level
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.animal.AnimalEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.State
import game.utils.Utility
import java.awt.Image

open class DefaultGameHandler(level: Level) : GameHandler(level) {
    override val borderImages: Array<Image?>
        get() {
            val SIDES = 4
            val pitch = arrayOfNulls<Image>(SIDES)
            for (i in 0 until SIDES) {
                val path = level.fileSystemHandler.getImageForCurrentLevel("border_$i.png")
                pitch[i] = Utility.loadImage(path)
            }
            return pitch
        }

    override fun generateStone() = GenerateStoneBehavior(level.field).invoke()

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