package com.diberardino.jbomb.domain.world.domain.entity.items

import com.diberardino.jbomb.domain.events.game.UpdateCurrentAvailableItemsEvent
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.enemy.Enemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.DestroyableBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.hard_block.HardBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.PistolExplosion
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.AbstractExplosion.Companion.SIZE
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.handler.ExplosionHandler
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.Explosive
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.items.ItemsTypes
import com.diberardino.jbomb.domain.world.domain.items.UsableItem
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.PistolPowerUp
import com.diberardino.jbomb.utility.Paths.itemsPath
import com.diberardino.jbomb.utility.Utility.timePassed
import com.diberardino.jbomb.utility.now

class PistolItem : UsableItem(), Explosive {
    private var bullets = 5

    override val explosionObstacles: Set<Class<out Entity>>
        get() = setOf(
                HardBlock::class.java,
                DestroyableBlock::class.java
        )

    override val explosionInteractionEntities: Set<Class<out Entity>>
        get() = setOf(
                Enemy::class.java,
                Bomb::class.java
        )

    override val imagePath: String
        get() = "$itemsPath/pistol.png"

    override val count: Int
        get() = bullets

    override val type: ItemsTypes = ItemsTypes.PistolItem

    override val maxExplosionDistance: Int
        get() = 3

    private fun setBullets(i: Int) {
        bullets = i
        UpdateCurrentAvailableItemsEvent().invoke(bullets)
    }

    private fun addBullets(i: Int) = setBullets(bullets + i)

    override fun use(itemId: Long?): Long {
        if (timePassed(owner.state.lastPlacedBombTime) < Bomb.PLACE_INTERVAL) {
            return -1
        }

        owner.state.lastPlacedBombTime = now()
        addBullets(-1)

        ExplosionHandler.instance.process {
            val explosion = PistolExplosion(
                    owner,
                    Coordinates.nextCoords(owner.info.position, owner.state.direction, SIZE),
                    owner.state.direction,
                    1,
                    this
            ).logic.explode()

            listOf(explosion)
        }

        if (bullets == 0) {
            remove()
        }

        return 1
    }

    override fun remove() {
        super.remove()
        owner.state.activePowerUps.remove(PistolPowerUp::class.java)
    }

    override fun combineItems(item: UsableItem) {
        addBullets((item as PistolItem).bullets)
    }
}