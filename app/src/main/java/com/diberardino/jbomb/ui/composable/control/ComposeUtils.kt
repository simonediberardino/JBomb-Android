package com.diberardino.jbomb.ui.composable.control

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.asImageBitmap

fun loadImageFromAssets(context: Context, fileName: String): androidx.compose.ui.graphics.ImageBitmap {
    val assetManager = context.assets
    val inputStream = assetManager.open(fileName)
    val bitmap = BitmapFactory.decodeStream(inputStream)
    return bitmap.asImageBitmap()
}