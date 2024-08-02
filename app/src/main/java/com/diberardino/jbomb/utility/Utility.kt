package com.diberardino.jbomb.utility

import android.content.Context
import android.graphics.BitmapFactory
import android.util.DisplayMetrics
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.diberardino.jbomb.JBombApplication
import com.diberardino.jbomb.data.cache.Cache
import com.diberardino.jbomb.values.Dimension
import com.diberardino.jbomb.values.Dimensions
import java.io.IOException


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

        Log.e("ScreenSize", "$width x $height")
        return@run Dimension(width, height)
    }

    /**
     * Converts a dimension in pixels to a dimension in screen units, based on the default screen size.
     *
     * @param dim The dimension in pixels to be converted.
     * @return The converted dimension in screen units.
     */

    fun px(dim: Int): Int = px(dim.toDouble()).toInt()

    fun px(dim: Double): Double = dim * (screenSize.width.toFloat() / Dimensions.DEFAULT_SCREEN_SIZE.width.toFloat())


    fun loadImage(context: Context, fileName: String): ImageBitmap {
        var fileName = fileName
        val cache = Cache.instance

        if (cache.hasInCache(fileName)) {
            return cache.queryCache(fileName) ?: throw RuntimeException()
        }

        // Adjust fileName if necessary
        fileName = fileName.replace("/src", "")

        val image = try {
            context.assets.open(fileName).use { inputStream ->
                BitmapFactory.decodeStream(inputStream) ?: throw RuntimeException()
            }
        } catch (e: Exception) {
            throw RuntimeException("Failed to load image: $fileName", e)
        }

        cache.saveInCache(fileName, image.asImageBitmap())
        return image.asImageBitmap()
    }

    fun loadImageGetPath(context: Context, fileName: String): String {
        var fileName = fileName

        // Adjust fileName if necessary
        fileName = fileName.replace("/src", "")

        val imagePath = try {
            context.assets.open(fileName).use { inputStream ->
                fileName
            }
        } catch (e: Exception) {
            throw RuntimeException("Failed to load image: $fileName", e)
        }

        return imagePath
    }


    fun fileExists(filePath: String): Boolean = try {
        JBombApplication.context.assets.open(filePath).use {
            true
        }
    } catch (e: IOException) {
        false
    }
}