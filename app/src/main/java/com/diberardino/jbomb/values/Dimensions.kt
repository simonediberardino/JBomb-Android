package com.diberardino.jbomb.values


object Dimensions {
    var DEFAULT_SCREEN_SIZE = Dimension(1920, 1080)
}

data class Dimension(val width: Int, val height: Int)