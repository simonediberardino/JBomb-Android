package com.diberardino.jbomb.domain.events.level.levels.lobby

import com.diberardino.jbomb.domain.events.level.info.imp.IslandLevelInfo
import com.diberardino.jbomb.domain.events.level.info.model.LevelInfo
import com.diberardino.jbomb.domain.level.levels.lobby.LobbyLevel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.invisible_block.InvisibleBlock
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.ui.screens.matchPanelSize
import com.diberardino.jbomb.values.Dimension
import com.diberardino.jbomb.values.Dimensions.GRID_SIZE

abstract class IslandLevel : LobbyLevel() {
    override val info: LevelInfo
        get() = IslandLevelInfo(this)

    override fun generateLevel() {
        generateInvisibleBlocks()
    }

    private fun generateInvisibleBlocks() {
        //BORDER INVISIBLE BLOCKS COLUMNS
        run {
            var i = 0
            while (i < matchPanelSize.width / GRID_SIZE - 1) {
                InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(i, 0))).logic.spawn()
                InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(i, (matchPanelSize.height / GRID_SIZE - 1).toInt()))).logic.spawn()
                i++
            }
        }
        //ROWS
        var i = 0
        while (i < matchPanelSize.height / GRID_SIZE - 1) {
            InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(0, i))).logic.spawn()
            InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension((matchPanelSize.width / GRID_SIZE).toInt() - 1, i))).logic.spawn()
            i++
        }
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(1, 1), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(2, 1), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(3, 1), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(4, 1), -GRID_SIZE / 6, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(6, 1), -GRID_SIZE / 3, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(11, 1), -GRID_SIZE / 3, -GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(1, 7), +GRID_SIZE / 2, -GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(2, 9), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(2, 8), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(8, 10), 0, -GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(9, 9), -GRID_SIZE / 3, -GRID_SIZE / 6)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(9, 8), GRID_SIZE / 6, -GRID_SIZE / 6)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(9, 7), GRID_SIZE / 6, -GRID_SIZE / 6)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(10, 6), GRID_SIZE * 2 / 3, -GRID_SIZE * 2 / 3)).logic.spawn(forceSpawn = true, forceCentering = false)


        //OUT OF BOUNDS BARRIER BLOCKS
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(10, 9), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(10, 8), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(11, 9), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(11, 8), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(10, 7), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(11, 7), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(11, 7), 0, -GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(10, 7), 0, -GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(9, 7), GRID_SIZE / 6, -GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(1, 8), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(1, 9), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(1, 2), GRID_SIZE / 3, -GRID_SIZE / 3)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(3, 6), GRID_SIZE / 6, +GRID_SIZE / 3)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(7, 4), GRID_SIZE / 6, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(7, 5), GRID_SIZE / 6, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(8, 5), -GRID_SIZE / 2, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(8, 4), -GRID_SIZE / 2, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(11, 3), -GRID_SIZE / 2, +GRID_SIZE / 3)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(10, 2), -GRID_SIZE / 6, +GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
    }

}