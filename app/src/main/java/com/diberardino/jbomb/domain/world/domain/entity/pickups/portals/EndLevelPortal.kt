package com.diberardino.jbomb.domain.world.domain.entity.pickups.portals

import androidx.compose.ui.graphics.ImageBitmap
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.level.levels.lobby.WorldSelectorLevel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.pickups.portals.base.Portal
import com.diberardino.jbomb.domain.world.domain.pickups.portals.base.logic.PortalLogic
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.utility.Paths.powerUpsFolder

class EndLevelPortal : Portal {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val graphicsBehavior: IEntityGraphicsBehavior = object : DefaultEntityGraphicsBehavior() {
        override fun getImage(entity: Entity): ImageBitmap? {
            return loadAndSetImage(entity = entity, imagePath = "$powerUpsFolder/end_game.gif")
        }
    }

    override val properties: EntityProperties = EntityProperties(type = EntityTypes.EndLevelPortal)

    override val logic: PortalLogic = object : PortalLogic(entity = this) {
        override fun canPickUp(bomberEntity: BomberEntity): Boolean {
            return JBomb.match.enemiesAlive <= 0
        }

        override fun doApply(player: BomberEntity) {
            super.doApply(player)

            val match = JBomb.match
            val currentLevel = match.currentLevel

            currentLevel.endLevel()
            JBomb.match.destroy(true)
            //DataInputOutput.getInstance().increaseLives()

            try {
                val nextLevelClass = if (currentLevel.info.isLastLevelOfWorld)
                    WorldSelectorLevel::class.java
                else currentLevel.info.nextLevel

                //JBomb.startLevel(nextLevelClass!!.getDeclaredConstructor().newInstance(), match.onlineGameHandler)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}