package game.domain.level.levels.lobby

import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.invisible_block.InvisibleBlock
import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
import game.domain.level.info.imp.IslandLevelInfo
import game.domain.level.info.model.LevelInfo
import game.presentation.ui.panels.game.PitchPanel
import java.awt.Dimension

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
            while (i < PitchPanel.DIMENSION.getWidth() / PitchPanel.GRID_SIZE - 1) {
                InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(i, 0))).logic.spawn()
                InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(i, (PitchPanel.DIMENSION.getHeight() / PitchPanel.GRID_SIZE - 1).toInt()))).logic.spawn()
                i++
            }
        }
        //ROWS
        var i = 0
        while (i < PitchPanel.DIMENSION.getHeight() / PitchPanel.GRID_SIZE - 1) {
            InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(0, i))).logic.spawn()
            InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension((PitchPanel.DIMENSION.getWidth() / PitchPanel.GRID_SIZE).toInt() - 1, i))).logic.spawn()
            i++
        }
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(1, 1), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(2, 1), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(3, 1), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(4, 1), -PitchPanel.GRID_SIZE / 6, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(6, 1), -PitchPanel.GRID_SIZE / 3, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(11, 1), -PitchPanel.GRID_SIZE / 3, -PitchPanel.GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(1, 7), +PitchPanel.GRID_SIZE / 2, -PitchPanel.GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(2, 9), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(2, 8), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(8, 10), 0, -PitchPanel.GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(9, 9), -PitchPanel.GRID_SIZE / 3, -PitchPanel.GRID_SIZE / 6)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(9, 8), PitchPanel.GRID_SIZE / 6, -PitchPanel.GRID_SIZE / 6)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(9, 7), PitchPanel.GRID_SIZE / 6, -PitchPanel.GRID_SIZE / 6)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(10, 6), PitchPanel.GRID_SIZE * 2 / 3, -PitchPanel.GRID_SIZE * 2 / 3)).logic.spawn(forceSpawn = true, forceCentering = false)


        //OUT OF BOUNDS BARRIER BLOCKS
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(10, 9), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(10, 8), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(11, 9), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(11, 8), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(10, 7), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(11, 7), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(11, 7), 0, -PitchPanel.GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(10, 7), 0, -PitchPanel.GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(9, 7), PitchPanel.GRID_SIZE / 6, -PitchPanel.GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(1, 8), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(1, 9), 0, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(1, 2), PitchPanel.GRID_SIZE / 3, -PitchPanel.GRID_SIZE / 3)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(3, 6), PitchPanel.GRID_SIZE / 6, +PitchPanel.GRID_SIZE / 3)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(7, 4), PitchPanel.GRID_SIZE / 6, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(7, 5), PitchPanel.GRID_SIZE / 6, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(8, 5), -PitchPanel.GRID_SIZE / 2, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(8, 4), -PitchPanel.GRID_SIZE / 2, 0)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(11, 3), -PitchPanel.GRID_SIZE / 2, +PitchPanel.GRID_SIZE / 3)).logic.spawn(forceSpawn = true, forceCentering = false)
        InvisibleBlock(Coordinates.fromRowAndColumnsToCoordinates(Dimension(10, 2), -PitchPanel.GRID_SIZE / 6, +PitchPanel.GRID_SIZE / 2)).logic.spawn(forceSpawn = true, forceCentering = false)
    }

}