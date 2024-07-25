package com.diberardino.jbomb.ui.composable.control

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.diberardino.jbomb.R
import com.diberardino.jbomb.ui.theme.JBombTheme
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt


@Composable
fun Joystick(
    modifier: Modifier = Modifier,
    onMove: (Offset) -> Unit
) {
    var knobPosition by remember { mutableStateOf(Offset.Zero) }
    val radius =
        with(LocalDensity.current) { 50.dp.toPx() } // Correctly calculate radius within composition

    val circleSize = 100.dp

    Box(
        modifier = modifier
            .size(circleSize)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        val newOffset = knobPosition + dragAmount
                        val distanceFromCenter =
                            sqrt(newOffset.x * newOffset.x + newOffset.y * newOffset.y)

                        knobPosition = if (distanceFromCenter <= radius) {
                            newOffset
                        } else {
                            val angle = atan2(newOffset.y, newOffset.x)
                            Offset(
                                radius * cos(angle),
                                radius * sin(angle)
                            )
                        }

                        onMove(knobPosition)
                    },
                    onDragEnd = {
                        knobPosition = Offset.Zero
                        onMove(knobPosition)
                    }
                )
            }
    ) {

        Image(
            modifier = Modifier
                .size(circleSize)
            ,
            painter = painterResource(id = R.drawable.joystick_background),
            contentDescription = "joystick_background"
        )

        Image(
            modifier = Modifier
                .offset {
                    IntOffset(
                        (knobPosition.x + (circleSize.toPx() / 2) / 2).roundToInt(),
                        (knobPosition.y + (circleSize.toPx() / 2) / 2).roundToInt()
                    )
                }
                .size(50.dp),
            painter = painterResource(id = R.drawable.joystick_handler),
            contentDescription = "joystick_handler"
        )
    }
}

@Composable
@Preview
fun JoystickPreview() {
    JBombTheme {
        Joystick(onMove = { offset ->
            println("Joystick moved to: $offset")
        })
    }
}