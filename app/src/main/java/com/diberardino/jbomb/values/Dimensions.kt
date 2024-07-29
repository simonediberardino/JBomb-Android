package com.diberardino.jbomb.values

import com.diberardino.jbomb.ui.screens.matchPanelSize
import com.diberardino.jbomb.utility.Utility

object Dimensions {
    var DEFAULT_SCREEN_SIZE = Dimension(1920, 1080)
    const val DEFAULT_PIXEL_UNIT = 6
    @kotlin.jvm.JvmField
    val PIXEL_UNIT: Int = Utility.px(DEFAULT_PIXEL_UNIT)
    @kotlin.jvm.JvmField
    val COMMON_DIVISOR = PIXEL_UNIT * 4
    @kotlin.jvm.JvmField
    val DEFAULT_SIZE = COMMON_DIVISOR * 2
    @kotlin.jvm.JvmField
    val GRID_SIZE: Int = COMMON_DIVISOR * 3

    val BLOCK_SIZE: Int get() {
        return matchPanelSize.width / 13
    }
}

data class Dimension(val width: Int, val height: Int)