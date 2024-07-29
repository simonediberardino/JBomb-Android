package com.diberardino.jbomb.domain.events.level.filesystem

import com.diberardino.jbomb.JBombApplication
import com.diberardino.jbomb.utility.Paths
import java.io.IOException

class LevelFileSystemHandler {
    fun getImageForCurrentLevel(path: String?): String = getFileForCurrentLevel("images/$path")

    fun getSoundForCurrentLevel(path: String?): String {
        return getFileForCurrentLevel("sound/$path")
    }

    /**
     * @return returns the path to the file: if a specific instance of the file exists for the current level, then return it, else return the current world instance;
     */
    private fun getFileForCurrentLevel(path: String): String {
        val context = JBombApplication.context
        fun getResourcePath(folder: String): String? {
            return try {
                context.assets.open("$folder/$path").use {
                    "$folder/$path"
                }
            } catch (e: IOException) {
                null
            }
        }

        return getResourcePath(Paths.currentLevelFolder)
            ?: getResourcePath(Paths.currentWorldCommonFolder)
            ?: getResourcePath("common")
            ?: "${Paths.worldsFolder}/common/$path"
    }
}