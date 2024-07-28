package com.diberardino.jbomb.domain.events.level.behavior

import game.domain.level.levels.Level

class PlayLevelSoundBehavior(private val level: Level): GameBehavior() {
    override fun hostBehavior(): () -> Unit {
        return {
            playLevelSound()
        }
    }

    override fun clientBehavior(): () -> Unit {
        return {
            playLevelSound()
        }
    }

    private fun playLevelSound() {
        /*val soundPath = level.info.levelBackgroundSound ?: return
        val classLoader = Thread.currentThread().contextClassLoader

        // Attempt to load the resource as an InputStream
        classLoader.getResourceAsStream(soundPath)?.use { soundStream ->
            // If the resource exists, play it using AudioManager
            AudioManager.instance.play(soundPath, true)
            level.currentLevelSound = soundPath
        }*/
    }
}