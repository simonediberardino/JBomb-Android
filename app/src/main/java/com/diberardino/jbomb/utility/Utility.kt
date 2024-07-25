package com.diberardino.jbomb.utility

import android.util.DisplayMetrics
import com.diberardino.jbomb.JBombApplication
import com.diberardino.jbomb.values.Dimension
import com.diberardino.jbomb.values.Dimensions


/**
 * A utility class containing helper methods for the game.
 */
object Utility {

    fun ensureRange(value: Float, min: Float, max: Float): Float {
        return value.coerceAtLeast(min).coerceAtMost(max)
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

}