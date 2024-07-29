package com.diberardino.jbomb.domain.world.domain.entity.items

import android.util.Log
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.events.game.UpdateCurrentAvailableItemsEvent
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb
import com.diberardino.jbomb.domain.world.domain.items.ItemsTypes
import com.diberardino.jbomb.domain.world.domain.items.UsableItem
import com.diberardino.jbomb.network.events.forward.UpdateInfoEventForwarder
import com.diberardino.jbomb.utility.Paths.entitiesFolder
import com.diberardino.jbomb.utility.Utility
import com.diberardino.jbomb.utility.now

class BombItem : UsableItem() {
    private lateinit var bombEntity: Bomb

    override fun use(itemId: Long?): Long {
        val match = JBomb.match
        val isLocalPlayer = owner == match.player

        val isBombPlacementIntervalValid = Utility.timePassed(
                owner.state.lastPlacedBombTime
        ) >= Bomb.PLACE_INTERVAL

        if (!isBombPlacementIntervalValid) {
            Log.e(this.javaClass.simpleName, "Cannot place bomb, too early")
            return -1
        }

        if (isLocalPlayer && owner.state.placedBombs >= owner.state.maxBombs) {
            Log.e(this.javaClass.simpleName, "owner.state.placedBombs: ${owner.state.placedBombs}")
            Log.e(this.javaClass.simpleName, "owner.state.maxBombs: ${owner.state.maxBombs}")
            Log.e(this.javaClass.simpleName, "Cannot place bomb, placedBombs >= maxBombs")
            return -1
        }

        if (isLocalPlayer && owner.state.currentBombs <= 0) {
            Log.e(this.javaClass.simpleName, "owner.state.currentBombs: ${owner.state.currentBombs}")
            Log.e(this.javaClass.simpleName, "Cannot place bomb, currentBombs <= 0")
            return -1
        }

        if (isLocalPlayer && owner.state.currExplosionLength <= 0) {
            Log.e(this.javaClass.simpleName, "owner.state.currExplosionLength: ${owner.state.currExplosionLength}")
            Log.e(this.javaClass.simpleName, "Cannot place bomb, currExplosionLength <= 0")
            return -1
        }

        if (isLocalPlayer && owner.state.currentBombs >= match.currentLevel.info.maxBombs) {
            Log.e(this.javaClass.simpleName, "owner.state.currentBombs: ${owner.state.currentBombs}")
            Log.e(this.javaClass.simpleName, "Cannot place bomb, currentBombs >= maxBombs")
            return -1
        }

        owner.state.lastPlacedBombTime = now()
        owner.state.placedBombs++
        owner.state.bombsSolid = (false)

        if (isLocalPlayer)
            UpdateCurrentAvailableItemsEvent().invoke(owner.state.currentBombs - 1)

        bombEntity = itemId?.takeIf { it != -1L }?.run {
            Bomb(this, owner)
        } ?: Bomb(owner)

        Log.e(this.javaClass.simpleName, "Spawning bomb with id ${bombEntity.info.id}")

        UpdateInfoEventForwarder().invoke((bombEntity as Entity).toEntityNetwork())

        bombEntity.logic.onExplodeCallback = {
            owner.state.placedBombs--

            if (isLocalPlayer)
                UpdateCurrentAvailableItemsEvent().invoke(owner.state.currentBombs + 1)
        }

        bombEntity.logic.spawn(forceSpawn = true)
        bombEntity.logic.trigger()

        return bombEntity.info.id
    }

    override fun combineItems(item: UsableItem) {
    }


    override val imagePath: String = "$entitiesFolder/bomb/bomb_0.png"

    override val count: Int
        get() = owner.state.currentBombs

    override val type: ItemsTypes = ItemsTypes.BombItem
}