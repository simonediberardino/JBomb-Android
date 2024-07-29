package com.diberardino.jbomb.utility

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.DisplayMetrics
import android.util.Log
import com.diberardino.jbomb.JBombApplication
import com.diberardino.jbomb.data.cache.Cache
import com.diberardino.jbomb.values.Dimension
import com.diberardino.jbomb.values.Dimensions


/**
 * A utility class containing helper methods for the game.
 */
object Utility {

    fun ensureRange(value: Float, min: Float, max: Float): Float {
        return value.coerceAtLeast(min).coerceAtMost(max)
    }

    private fun chooseRandom(chance: Int): Boolean {
        var chance = chance
        chance = 0.coerceAtLeast(chance)
        chance = 100.coerceAtMost(chance)
        return Math.random() * 100 <= chance
    }


    fun runPercentage(chance: Int, runnable: Runnable) {
        if (chooseRandom(chance)) runnable.run()
    }

    fun timePassed(time: Long): Long {
        return now() - time
    }

    fun isValueInRange(value: Int, min: Int, max: Int): Boolean {
        return value in min..max
    }

    val screenSize: Dimension = run {
        // Get the display metrics
        val displayMetrics = DisplayMetrics()
        val context = JBombApplication.activity
        context.windowManager.defaultDisplay!!.getMetrics(displayMetrics)

        // Retrieve width and height in pixels
        val width: Int = displayMetrics.widthPixels
        val height: Int = displayMetrics.heightPixels

        return@run Dimension(width, height)
    }

    /**
     * Converts a dimension in pixels to a dimension in screen units, based on the default screen size.
     *
     * @param dim The dimension in pixels to be converted.
     * @return The converted dimension in screen units.
     */

    fun px(dim: Int): Int = px(dim.toDouble()).toInt()

    fun px(dim: Double): Double = dim * (screenSize.width / Dimensions.DEFAULT_SCREEN_SIZE.width)


    fun loadImage(context: Context, fileName: String): Bitmap {
        var fileName = fileName
        val cache = Cache.instance

        if (cache.hasInCache(fileName)) {
            return cache.queryCache(fileName) ?: throw RuntimeException()
        }

        // Adjust fileName if necessary
        fileName = fileName.replace("/src", "")
        Log.i(this.javaClass.simpleName, "Loading $fileName")

        val image: Bitmap = try {
            context.assets.open(fileName).use { inputStream ->
                BitmapFactory.decodeStream(inputStream) ?: throw RuntimeException()
            }
        } catch (e: Exception) {
            throw RuntimeException("Failed to load image: $fileName", e)
        }

        cache.saveInCache(fileName, image)
        return image
    }

}