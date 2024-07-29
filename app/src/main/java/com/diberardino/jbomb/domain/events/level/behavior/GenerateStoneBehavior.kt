package com.diberardino.jbomb.domain.events.level.behavior

import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.stone_block.StoneBlock
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.ui.screens.matchPanelSize
import com.diberardino.jbomb.values.Dimensions.GRID_SIZE


class GenerateStoneBehavior(
) : GameBehavior() {
    override fun hostBehavior(): () -> Unit {
        return {
            generateStone()
        }
    }

    override fun clientBehavior(): (() -> Unit) {
        return {}
    }


    private fun generateStone() {
        // Set the current x and y coordinates to the top-left corner of the game board.
        var currX = 0
        var currY = GRID_SIZE


        // Loop through the game board, adding stone blocks at every other grid position.
        while (currY < matchPanelSize.height - GRID_SIZE) {
            while (currX < matchPanelSize.width - GRID_SIZE && currX + GRID_SIZE * 2 <= matchPanelSize.width) {
                // Move the current x coordinate to the next grid position.
                currX += GRID_SIZE

                // Create a new stone block at the current coordinates and spawn it on the game board.
                StoneBlock(Coordinates(currX, currY)).logic.spawn()

                // Move the current x coordinate to the next grid position.
                currX += GRID_SIZE
            }
            // Move the current x coordinate back to the left side of the game board.
            currX = 0

            // Move the current y coordinate down to the next row of grid positions.
            currY += GRID_SIZE * 2
        }
    }
}