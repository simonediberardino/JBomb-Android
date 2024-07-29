package com.diberardino.jbomb

import android.util.Log
import com.diberardino.jbomb.data.collections.SortedLinkedList
import com.diberardino.jbomb.domain.events.game.ResetBombsVariablesGameEvent
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.tasks.GameTickerObservable
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.Player
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb
import com.diberardino.jbomb.domain.world.domain.entity.items.BombItem
import com.diberardino.jbomb.domain.world.domain.items.UsableItem
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.base.PowerUp
import com.diberardino.jbomb.network.events.forward.UseItemHttpEventForwarder
import com.diberardino.jbomb.network.gamehandler.ClientGameHandler
import com.diberardino.jbomb.network.gamehandler.OnlineGameHandler
import com.diberardino.jbomb.network.gamehandler.ServerGameHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import java.util.LinkedList

class JBombMatch(
    var currentLevel: Level,
    val onlineGameHandler: OnlineGameHandler?
) {
    companion object {
        var defaultPort: Int = 30960
    }

    val scope = CoroutineScope(Dispatchers.IO)

    // Player information (nullable)
    var player: Player? = null

    var players: MutableList<BomberEntity> = mutableListOf()
        private set

    // List of bombs in the game
    val bombs = ArrayList<Bomb>()

    // Current game state (default: false)
    var gameState = false

    var pausePanelVisible = false

    // Number of enemies currently alive (read-only)
    var enemiesAlive = 0
        private set

    /**
     * Checks if the game is running in client mode.
     *
     * @return True if the client game handler is not null and connected, false otherwise.
     */
    val isClient: Boolean
        get() = onlineGameHandler != null && onlineGameHandler is ClientGameHandler

    /**
     * Checks if the game is running in server mode.
     *
     * @return True if the server game handler is not null and running, false otherwise.
     */
    val isServer: Boolean
        get() = onlineGameHandler is ServerGameHandler || !isClient && onlineGameHandler == null


    // List of entities sorted by a linked list
    private val _entitiesList: SortedLinkedList<Entity> = SortedLinkedList()
    private val _entitiesMap: HashMap<Long, Entity> = HashMap()
    private val _despawnedEntitiesMap: HashMap<Long, Pair<Class<out Entity>, Entity>> = HashMap()

    // Observable for game tick events (nullable)
    var gameTickerObservable: GameTickerObservable? = GameTickerObservable(scope)
        private set

    /**
     * Adds a bomb to the list of bombs in the game.
     *
     * @param bomb The Bomb to be added.
     */
    fun addBomb(bomb: Bomb) {
        bombs.add(bomb)
    }

    /**
     * Removes a bomb from the list of bombs in the game.
     *
     * @param bomb The Bomb to be removed.
     */
    fun removeBomb(bomb: Bomb) {
        bombs.remove(bomb)
    }

    /**
     * Returns a copy of the list of _entities to prevent external modifications.
     */
    fun getEntities(): List<Entity> = synchronized(_entitiesList) { LinkedList(_entitiesList) }

    fun getDeadEntities(): HashMap<Long, Pair<Class<out Entity>, Entity>> = synchronized(_despawnedEntitiesMap) { _despawnedEntitiesMap }

    fun getEntityById(entityId: Long): Entity? = _entitiesMap[entityId]

    fun addEntity(entity: Entity) {
        synchronized(_entitiesList) {
            _entitiesList.add(entity)
        }

        synchronized(_entitiesMap) {
            _entitiesMap.put(entity.info.id, entity)
        }

        synchronized(_despawnedEntitiesMap) {
            _despawnedEntitiesMap.remove(entity.info.id)
        }
    }

    fun removeEntity(entity: Entity) {
        if (entity.state.canRespawn) {
            _despawnedEntitiesMap[entity.info.id] = Pair(entity.javaClass, entity)
        }

        synchronized(_entitiesList) {
            _entitiesList.removeIf { it.info.id == entity.info.id }
        }

        synchronized(_entitiesMap) {
            _entitiesMap.remove(entity.info.id)
        }

        entity.logic.onRemoved()
    }

    /**
     * Gives an item to the specified owner (BomberEntity).
     *
     * @param owner The BomberEntity receiving the item.
     * @param item The UsableItem to be given.
     * @param combineSameItem Flag indicating whether to combine items of the same type.
     */
    fun give(owner: BomberEntity, item: UsableItem, combineSameItem: Boolean = false) {
        if (combineSameItem && owner.state.weapon.javaClass == item.javaClass) {
            // Combine items if requested and owner already has the same type of item
            owner.state.weapon.combineItems(item)
        } else {
            // Set the new item as the owner's weapon and update related components
            owner.state.weapon = item
            owner.state.weapon.owner = owner
            updateWeaponUi()
        }
    }

    fun updateWeaponUi() {
        
    }
    
    fun useItem(owner: BomberEntity) {
        val currItem = owner.state.weapon

        val id = currItem.use()

        if (id != -1L) {
            Log.e(this.javaClass.simpleName, "Used item with id $id")
            UseItemHttpEventForwarder().invoke(owner.toEntityNetwork(), currItem.type, id)
        }
    }

    /**
     * Removes the current item from the specified owner (BomberEntity) and replaces it with a BombItem.
     *
     * @param owner The BomberEntity from which to remove the item.
     */
    fun removeItem(owner: BomberEntity) {
        // Replace the current item with a BombItem and update related components
        owner.state.weapon = BombItem()
        owner.state.weapon.owner = owner

        ResetBombsVariablesGameEvent().invoke(null)
        updateWeaponUi()
    }

    fun refreshPowerUpsUi(powerUps: List<Class<out PowerUp>>) {

    }


    private fun cancelCoroutineJob() {
        scope.cancel()
    }

    /**
     * Destroys all _entities in the game by despawning them.
     */
    private fun destroyEntities() {
        getEntities().forEach { it.logic.despawn() }
    }

    /**
     * Clears the graphics callback in the Bomberman frame's pitch panel.
     */
    private fun clearGraphicsCallback() {
        //JBomb.JBombFrame.pitchPanel.clearGraphicsCallback()
    }

    /**
     * Stops the sound associated with the current game level.
     */
    private fun stopLevelSound() {
        //AudioManager.instance.stop(currentLevel.currentLevelSound ?: return)
    }

    /**
     * Resets the game state variables, setting them to default values or null.
     */
    private fun resetState() {
        player = null
        enemiesAlive = 0
        _entitiesList.clear()
    }

    /**
     * Stops the movement task for mouse controllers.
     */
    private fun stopMovementTask() {
        //mouseControllerManager.stopMovementTask()
    }

    /**
     * Unregisters all observables and controllers associated with the game.
     */
    private fun unregisterAllObservablesAndControllers() {
        gameTickerObservable?.unregisterAll()
        //controllerManager?.unregisterAll()
        gameTickerObservable = null
        //controllerManager = null
    }

    /**
     * Initiates the garbage collection process to release unused memory.
     */
    fun performGarbageCollection() {
        System.gc()
    }


    /**
     * Performs cleanup operations and releases resources associated with the game.
     */
    fun destroy(disconnect: Boolean = false) {
        if (isServer || disconnect) {
            onlineGameHandler?.disconnect()
        }

        // Pause the game to ensure safe destruction
        //pauseGame(showUi = false, freeze = true)

        // Cancel job
        cancelCoroutineJob()

        // Destroy all _entities in the game
        destroyEntities()

        // Clear graphics callback in the Bomberman frame's pitch panel
        clearGraphicsCallback()

        // Stop the sound associated with the current game level
        stopLevelSound()

        // Reset the game state variables
        resetState()

        // Stop the movement task for mouse controllers
        stopMovementTask()

        // Unregister all observables and controllers
        unregisterAllObservablesAndControllers()

        // Perform garbage collection to release memory
        performGarbageCollection()

        //cleanLevelUi()

        //ToastHandler.getInstance().cancel()
    }

    fun updateEnemiesAliveCount(count: Int) {
        enemiesAlive = count
    }

}