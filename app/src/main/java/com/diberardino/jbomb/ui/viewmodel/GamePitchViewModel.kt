package com.diberardino.jbomb.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.tasks.observer.Observable2
import com.diberardino.jbomb.domain.tasks.observer.Observer2
import com.diberardino.jbomb.ui.state.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GamePitchViewModel: ViewModel() {
    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> get() = _gameState.asStateFlow()

    init {
        JBomb.match.gameTickerObservable?.register(object: Observer2 {
            override fun update(arg: Observable2.ObserverParam) {
                _gameState.value.copy(
                    entities = JBomb.match.getEntities(),
                )
            }
        })
    }
}