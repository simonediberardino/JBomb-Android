package com.diberardino.jbomb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diberardino.jbomb.domain.events.level.levels.Level
import com.diberardino.jbomb.network.gamehandler.OnlineGameHandler

class JBombMatchViewModelFactory(
    private val level: Level,
    private val onlineGameHandler: OnlineGameHandler?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JBombMatchViewModel::class.java)) {
            return JBombMatchViewModel(level, onlineGameHandler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}