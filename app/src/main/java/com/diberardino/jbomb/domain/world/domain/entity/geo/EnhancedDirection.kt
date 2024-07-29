package com.diberardino.jbomb.domain.world.domain.geo

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.domain.world.domain.entity.geo.Direction
import com.diberardino.jbomb.ui.screens.matchPanelSize

enum class EnhancedDirection {
    LEFTUP, LEFTDOWN, RIGHTUP, RIGHTDOWN;

    fun toDirection(): Array<Direction> {
        return when (this) {
            LEFTUP -> arrayOf(Direction.LEFT, Direction.UP)
            LEFTDOWN -> arrayOf(Direction.LEFT, Direction.DOWN)
            RIGHTDOWN -> arrayOf(Direction.RIGHT, Direction.DOWN)
            RIGHTUP -> arrayOf(Direction.RIGHT, Direction.UP)
        }
    }

    fun opposite(direction: Direction): EnhancedDirection? {
        val array = toDirection()
        for (i in array!!.indices) {
            if (array[i] === direction) array[i] = direction.opposite()
        }
        return toEnhancedDirection(array)
    }

    companion object {
        fun toEnhancedDirection(directions: Array<Direction>?): EnhancedDirection? {
            var vertical: Direction? = null
            var horizontal: Direction? = null
            if (directions!!.size != 2) {
                return null
            }
            for (d in directions) {
                when (d) {
                    Direction.DOWN, Direction.UP -> vertical = d
                    Direction.RIGHT, Direction.LEFT -> horizontal = d
                }
            }
            if (vertical === Direction.UP && horizontal === Direction.RIGHT) return RIGHTUP
            if (vertical === Direction.DOWN && horizontal === Direction.RIGHT) return RIGHTDOWN
            if (vertical === Direction.UP && horizontal === Direction.LEFT) return LEFTUP
            return if (vertical === Direction.DOWN && horizontal === Direction.LEFT) LEFTDOWN else null
        }

        fun randomDirectionTowardsCenter(entity: Entity): EnhancedDirection? {
            val coords = entity.info.position

            val centerEntityCoords = Coordinates(coords.x + entity.state.size / 2, coords.y + entity.state.size / 2)
            val newHorizontalDirection: Direction = if (centerEntityCoords.x > matchPanelSize.width / 2) Direction.LEFT else Direction.RIGHT
            val newVerticalDirection: Direction = if (centerEntityCoords.y < matchPanelSize.height / 2) Direction.DOWN else Direction.UP
            return toEnhancedDirection(arrayOf(newHorizontalDirection, newVerticalDirection))
        }
    }
}