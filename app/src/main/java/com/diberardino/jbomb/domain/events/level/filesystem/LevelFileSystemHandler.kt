package game.domain.level.filesystem

import game.utils.file_system.Paths

class LevelFileSystemHandler {
    fun getImageForCurrentLevel(path: String?): String = getFileForCurrentLevel("images/$path")

    fun getSoundForCurrentLevel(path: String?): String {
        return getFileForCurrentLevel("sound/$path")
    }

    /**
     * @return returns the path to the file: if a specific instance of the file exists for the current level, then return it, else return the current world instance;
     */
    private fun getFileForCurrentLevel(path: String): String {
        val classLoader = Thread.currentThread().contextClassLoader

        fun getResourcePath(folder: String): String? {
            return classLoader.getResourceAsStream("$folder/$path")?.use {
                "$folder/$path"
            }
        }

        return getResourcePath(Paths.currentLevelFolder)
                ?: getResourcePath(Paths.currentWorldCommonFolder)
                ?: getResourcePath("common")
                ?: "${Paths.worldsFolder}/common/$path"
    }
}