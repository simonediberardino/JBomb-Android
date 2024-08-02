package com.diberardino.jbomb.ui.state

import com.diberardino.jbomb.domain.events.level.info.model.LevelInfo
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity

data class GameState(
    val entities: List<Entity> = emptyList(),
    val paused: Boolean = false,
    val levelInfo: LevelInfo?,
    val borderImages: List<String?> = listOf()
)