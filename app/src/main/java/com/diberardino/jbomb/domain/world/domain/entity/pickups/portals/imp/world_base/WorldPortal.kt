package com.diberardino.jbomb.domain.world.domain.pickups.portals.imp.world_base

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.events.level.levels.world1.World1Level1
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.pickups.portals.base.Portal
import com.diberardino.jbomb.domain.world.domain.pickups.portals.base.logic.PortalLogic
import com.diberardino.jbomb.domain.world.domain.entity.pickups.portals.imp.world_base.state.WorldPortalState
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import com.diberardino.jbomb.utility.Paths.getWorldSelectorPortalPath
import java.lang.reflect.InvocationTargetException
import java.util.Optional

abstract class WorldPortal(coordinates: Coordinates?, val worldId: Int) : Portal(coordinates) {
    constructor(worldId: Int) : this(null, worldId)


    override val logic: PortalLogic = object : PortalLogic(entity = this) {
        override fun onSpawn() {
            super.onSpawn()
            state.defaultCoords?.let {
                info.position = it
            }
        }

        override fun doApply(player: BomberEntity) {
            super.doApply(player)
            try {
                // Get the ID of the last level and last world from the player data object
                val savedLastLevelId = 1//DataInputOutput.getInstance().lastLevelId
                val savedLastWorldId = 1//DataInputOutput.getInstance().lastWorldId

                // Get the class of the first level for the current world from the ID_TO_FIRST_LEVEL_MAP
                val firstLevelOfCurrWorld = Level.ID_TO_FIRST_LEVEL_MAP.getOrDefault(worldId, World1Level1::class.java)

                // Initialize the level to start

                // Check if the saved last world ID matches the current world ID
                val levelToStart: Level = if (savedLastWorldId == worldId) {
                    // Find the class of the last level using the saved last world ID and last level ID
                    val lastLevelOpt = findLastLevel(savedLastWorldId, savedLastLevelId)
                    val levelClass = if (lastLevelOpt.isPresent) lastLevelOpt.get() else firstLevelOfCurrWorld

                    // Create an instance of the level class
                    levelClass.getConstructor().newInstance()
                } else {
                    // Use the class of the first level for the current world
                    firstLevelOfCurrWorld.getConstructor().newInstance()
                }

                JBomb.match.destroy(true)
                // Start the level with the obtained level instance
                //JBomb.startLevel(levelToStart, JBomb.match.onlineGameHandler)
            } catch (e: InstantiationException) {
                // Print the stack trace if there is an exception
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            }
        }

        /**
         * Finds the class of the last level based on the saved last world ID and last level ID.
         *
         * @param savedLastWorldId the saved last world ID
         * @param savedLastLevelId the saved last level ID
         * @return an optional containing the class of the last level, if found
         */
        private fun findLastLevel(savedLastWorldId: Int, savedLastLevelId: Int): Optional<Class<out Level>> {
            return Level.findLevel(savedLastWorldId, savedLastLevelId)
        }
    }

    abstract override val state: WorldPortalState

    override val image: EntityImageModel = EntityImageModel(
            entity = this,
            entitiesAssetsPath = getWorldSelectorPortalPath(worldId),
    )

    internal object DEFAULT {
        val SIZE = PowerUp.DEFAULT.SIZE * 3
    }
}