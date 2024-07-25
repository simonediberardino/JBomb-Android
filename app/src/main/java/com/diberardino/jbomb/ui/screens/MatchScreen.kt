package com.diberardino.jbomb.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.diberardino.jbomb.ui.composable.control.Joystick
import com.diberardino.jbomb.ui.theme.paddingScreenDevice

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
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Joystick(onMove = {})
                }

                // Game pitch centered in the available space
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(13f / 11f)
                        .background(Color.Green)
                )

                // Empty column on the right side
                Column(
                    modifier = Modifier
                        .weight(1f)
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
