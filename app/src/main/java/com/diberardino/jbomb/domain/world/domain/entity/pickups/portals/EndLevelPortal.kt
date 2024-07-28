package com.diberardino.jbomb.domain.world.domain.pickups.portals

import game.JBomb
import game.data.data.DataInputOutput
import game.domain.level.levels.lobby.WorldSelectorLevel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.DefaultEntityGraphicsBehavior
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.pickups.portals.base.Portal
import com.diberardino.jbomb.domain.world.domain.pickups.portals.base.logic.PortalLogic
import game.domain.world.types.EntityTypes
import game.utils.file_system.Paths.powerUpsFolder
import java.awt.image.Bitmap

class EndLevelPortal : Portal {
    constructor(id: Long) : super(id)
    constructor(coordinates: Coordinates?) : super(coordinates)

    override val graphicsBehavior: IEntityGraphicsBehavior = object : DefaultEntityGraphicsBehavior() {
        override fun getImage(entity: Entity): Bitmap? {
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
            JBomb.destroyLevel(true)
            DataInputOutput.getInstance().increaseLives()

            try {
                val nextLevelClass = if (currentLevel.info.isLastLevelOfWorld)
                    WorldSelectorLevel::class.java
                else currentLevel.info.nextLevel

                JBomb.startLevel(nextLevelClass!!.getDeclaredConstructor().newInstance(), match.onlineGameHandler)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}