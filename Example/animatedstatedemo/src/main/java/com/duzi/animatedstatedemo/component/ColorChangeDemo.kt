package com.duzi.animatedstatedemo.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class BoxColor {
    Red, Magenta
}

@Composable
fun ColorChangeDemo() {
    var colorState by remember { mutableStateOf(BoxColor.Red) }

    val animatedColor: Color by animateColorAsState(
        targetValue = when (colorState) {
            BoxColor.Red -> Color.Red
            BoxColor.Magenta -> Color.Magenta
        },
        animationSpec = tween(durationMillis = 4500)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .size(200.dp)
                .background(animatedColor)
        )

        Button(
            onClick = {
                colorState = when (colorState) {
                    BoxColor.Red -> BoxColor.Magenta
                    BoxColor.Magenta -> BoxColor.Red
                }
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Change Color")
        }
    }
}