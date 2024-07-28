package game.domain.level.eventhandler.model

import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity

interface LevelEventHandler {
    fun onDefeatGameEvent()
    fun onEnemyDespawned()
    fun onKilledEnemy()
    fun onRoundPassedGameEvent()
    fun onScoreGameEvent(arg: Int)
    fun onPurchaseItem(price: Int)
    fun onUpdateCurrentAvailableBombsEvent(arg: Int)
    fun onUpdateBombsLengthEvent(arg: Int, save: Boolean)
    fun onAllEnemiesEliminated()
    fun onDeathGameEvent()
    fun initBombsVariables()
    fun onUpdateMaxBombsGameEvent(arg: Int, save: Boolean)
    fun resetBombsVariables()
}