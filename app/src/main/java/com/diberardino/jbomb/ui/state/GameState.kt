package com.diberardino.jbomb.ui.state

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity

data class GameState(
    val entities: List<Entity> = emptyList(),
    val paused: Boolean = false
)