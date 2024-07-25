package com.diberardino.jbomb.utility

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap

class UiUtils {
    @Composable
    fun LoadImageFromAssets(context: Context, fileName: String): androidx.compose.ui.graphics.ImageBitmap {
        val assetManager = context.assets
        val inputStream = assetManager.open(fileName)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        return bitmap.asImageBitmap()
    }
}