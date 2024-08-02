package com.diberardino.jbomb.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.JBomb.match
import com.diberardino.jbomb.JBombMatch
import com.diberardino.jbomb.domain.events.level.behavior.GeneratePlayerBehavior
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.domain.tasks.observer.Observable2
import com.diberardino.jbomb.domain.tasks.observer.Observer2
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.Bomb
import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
import com.diberardino.jbomb.network.gamehandler.OnlineGameHandler
import com.diberardino.jbomb.ui.state.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JBombMatchViewModel(
    private val level: Level,
    private val onlineGameHandler: OnlineGameHandler?
) : ViewModel() {

    private val _gameState = MutableStateFlow(GameState(
        levelInfo = null
    ))
    val gameState: StateFlow<GameState> get() = _gameState.asStateFlow()

    init {
        val match = JBombMatch(level, onlineGameHandler)
        JBomb.startMatch(match)

        GeneratePlayerBehavior(Coordinates(150, 150)).invoke()

        _gameState.value = _gameState.value.copy(
            entities = match.getEntities(),
            levelInfo = level.info,
            borderImages = match.currentLevel.gameHandler.borderImages.asList()
        )

        // Register observer for game ticker
        match.gameTickerObservable?.register(object : Observer2 {
            override fun update(arg: Observable2.ObserverParam) {
                viewModelScope.launch {
                    Log.e("Tick", "Entities: ${match.getEntities()}")
                    _gameState.value = _gameState.value.copy(
                        entities = match.getEntities(),
                        levelInfo = level.info,
                        borderImages = match.currentLevel.gameHandler.borderImages.asList()
                    )
                }
            }
        })
    }

    fun addBomb(bomb: Bomb) {
        viewModelScope.launch {
            match.addBomb(bomb)
        }
    }

    fun removeBomb(bomb: Bomb) {
        viewModelScope.launch {
            match.removeBomb(bomb)
        }
    }

    fun useItem(owner: BomberEntity) {
        viewModelScope.launch {
            match.useItem(owner)
        }
    }

    // Implement other necessary actions
    // ...

    override fun onCleared() {
        super.onCleared()
        // Clean up resources
        match.destroy()
    }
}
