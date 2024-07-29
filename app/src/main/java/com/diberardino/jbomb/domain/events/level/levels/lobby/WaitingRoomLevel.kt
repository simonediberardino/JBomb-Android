package com.diberardino.jbomb.domain.events.level.levels.lobby

import com.diberardino.jbomb.localization.Localization

class WaitingRoomLevel : IslandLevel() {
    override fun generateLevel() {
        super.generateLevel()

        /*ToastHandler.getInstance().show(
                Localization.get(Localization.CONNECTING),
                true,
                false
        )*/
    }

    override fun toString(): String {
        return Localization.get(Localization.WAITING_ROOM)
    }
}