package com.diberardino.jbomb.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import com.diberardino.jbomb.ui.composable.control.Joystick
import com.diberardino.jbomb.ui.composable.control.loadImageFromAssets
import com.diberardino.jbomb.ui.theme.paddingScreenDevice
import com.diberardino.jbomb.values.Dimension


var matchPanelSize = Dimension(0, 0)
    private set

@Composable
fun MatchScreen() {
    JBombScreen {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingScreenDevice)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingScreenDevice)
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.35f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Joystick(onMove = {})
                }

                // Game pitch centered in the available space
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .aspectRatio(14f / 11f)
                ) {
                    GamePitchWithBorders()
                }

                // Empty column on the right side
                Column(
                    modifier = Modifier
                        .weight(0.35f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // You can add other content here if needed
                }
            }
        }
    }
}


@Composable
fun GamePitchWithBorders() {
    val context = LocalContext.current

    // Load images from assets
    val topBorderImage =
        remember { loadImageFromAssets(context, "worlds/0/common/images/border_3.png") }
    val bottomBorderImage =
        remember { loadImageFromAssets(context, "worlds/0/common/images/border_1.png") }
    val leftBorderImage =
        remember { loadImageFromAssets(context, "worlds/0/common/images/border_0.png") }
    val rightBorderImage =
        remember { loadImageFromAssets(context, "worlds/0/common/images/border_2.png") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                bitmap = topBorderImage,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize())
            {
                Image(
                    bitmap = leftBorderImage,
                    contentDescription = null,
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .background(Color.Red)
                        .weight(1f)
                        .aspectRatio(13f / 11f)
                        .fillMaxSize()
                        .onSizeChanged { newSize ->
                            matchPanelSize = Dimension(newSize.width, newSize.height)
                        }
                ) {
                }


                Image(
                    bitmap = rightBorderImage,
                    contentDescription = null,
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
            }

            Image(
                bitmap = bottomBorderImage,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

        }

    }
}
