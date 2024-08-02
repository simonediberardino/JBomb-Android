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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diberardino.jbomb.domain.events.level.levels.world1.World1Level1
import com.diberardino.jbomb.ui.composable.control.Joystick
import com.diberardino.jbomb.ui.theme.paddingScreenDevice
import com.diberardino.jbomb.ui.viewmodel.JBombMatchViewModel
import com.diberardino.jbomb.ui.viewmodel.JBombMatchViewModelFactory
import com.diberardino.jbomb.utility.Utility.loadImage
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
    val factory = JBombMatchViewModelFactory(World1Level1(), null)
    val viewModel: JBombMatchViewModel = viewModel(factory = factory)

    val context = LocalContext.current

    val gameState by viewModel.gameState.collectAsState()

    // Load images from assets
    val topBorderImage =
        remember { loadImage(context, gameState.borderImages[3]!!) }
    val bottomBorderImage =
        remember { loadImage(context, gameState.borderImages[1]!!) }
    val leftBorderImage =
        remember { loadImage(context, gameState.borderImages[0]!!) }
    val rightBorderImage =
        remember { loadImage(context, gameState.borderImages[2]!!) }

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
                    .fillMaxSize()
            )
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
                    gameState.levelInfo?.pitchImagePath?.let {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                bitmap = loadImage(context, it),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )

                            GamePitch(viewModel)
                        }
                    }
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
