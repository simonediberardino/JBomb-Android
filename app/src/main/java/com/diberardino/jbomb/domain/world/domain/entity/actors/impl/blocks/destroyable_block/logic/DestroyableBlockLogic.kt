package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.logic

import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.base_block.logic.BlockEntityLogic
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.DestroyableBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.AbstractExplosion
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.pickups.portals.EndLevelPortal
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import com.diberardino.jbomb.utility.Utility

class DestroyableBlockLogic(override val entity: DestroyableBlock) : BlockEntityLogic(entity = entity) {
    companion object {
        const val POWER_UP_SPAWN_CHANGE = 33
    }

    override fun onDespawn() {
        super.onDespawn()

        val powerUpClass = entity.state.powerUpClass ?: return

        val spawnPercentage = if (powerUpClass == EndLevelPortal::class.java) 100 else POWER_UP_SPAWN_CHANGE

        Utility.runPercentage(spawnPercentage) {
            val powerUp: PowerUp
            try {
                powerUp = powerUpClass
                        .getConstructor(Coordinates::class.java)
                        .newInstance(entity.info.position)

                powerUp.logic.spawn(true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onExplosion(explosion: AbstractExplosion?) {
        super.onExplosion(explosion ?: return)
        explosion.logic.attack(entity)
    }
}