package com.diberardino.jbomb.utility

object SkinUtilities {
    fun getSkinName(id: String): String {
        return "skin$id"
    }

    fun getSkinId(name: String): Int {
        return name.replace("skin", "").toInt()
    }
}