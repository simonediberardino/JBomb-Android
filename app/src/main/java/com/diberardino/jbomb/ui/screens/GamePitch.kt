package com.diberardino.jbomb.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.diberardino.jbomb.ui.viewmodel.JBombMatchViewModel
import com.diberardino.jbomb.utility.UiUtils.pxToDp
import kotlin.math.ceil

@Composable
fun GamePitch(viewModel: JBombMatchViewModel) {
    val context = LocalContext.current
    val gameState by viewModel.gameState.collectAsState()
    val entities = remember { gameState.entities }

    // Iterate over each entity
    entities.forEach { entity ->
        val imageBitmap = entity.graphicsBehavior.getImage(entity) ?: return@forEach
        val path: String = entity.image.imagePath

        val widthRatio = entity.graphicsBehavior.getHitboxSizeToWidthRatio(entity, path)
        val heightRatio = entity.graphicsBehavior.getHitboxSizeToHeightRatio(entity, path)
        val paddingWidth = entity.graphicsBehavior.calculateAndGetPaddingWidth(entity, widthRatio.toDouble())
        val paddingHeight = entity.graphicsBehavior.calculateAndGetPaddingTop(entity, heightRatio.toDouble())

        val alpha = entity.state.alpha

        val xOffset = pxToDp(entity.info.position.x.toFloat(), context) - paddingWidth.dp
        val yOffset = pxToDp(entity.info.position.y.toFloat(), context) - paddingHeight.dp

        val width = ceil(entity.state.size / widthRatio)
        val height =  ceil(entity.state.size / heightRatio)

        // Draw image with modifications
        Image(
            bitmap = imageBitmap,
            contentDescription = entity.info.toString(),
            modifier = Modifier
                .offset(x = xOffset, y = yOffset)
                .width(pxToDp(width.toFloat(), context))
                .height(pxToDp(height.toFloat(), context))
                .graphicsLayer(alpha = alpha)
        )
    }
}