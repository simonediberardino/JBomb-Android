package com.diberardino.jbomb.utility

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object UiUtils {
    fun pxToDp(px: Float, context: Context): Dp {
        val density = context.resources.displayMetrics.density
        return (px / density).dp
    }
}