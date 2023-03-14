package com.duzi.compose.remind.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

fun Modifier.exampleLayout(
    x: Int,
    y: Int,
) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x, y)
    }
}

fun Modifier.exampleLayout(
    fraction: Float
) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    val x = -(placeable.width * fraction).roundToInt()
    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x, 0)
    }
}

@Composable
fun ColorBox(modifier: Modifier) {
    Box(
        modifier = Modifier.padding(1.dp)
            .size(50.dp, 10.dp)
            .then(modifier)
    )
}

@Preview(showBackground = true)
@Composable
fun ColorBoxPreview() {
    Box(modifier = Modifier.size(300.dp, 80.dp).background(Color.Yellow)) {
        ColorBox(
            modifier = Modifier
                .exampleLayout(50, 50)
                .background(Color.Blue)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExampleLayoutPreview() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(120.dp, 80.dp)
            .background(Color.Yellow)
    ) {
        Column {
            ColorBox(modifier = Modifier.exampleLayout(0f).background(Color.Blue))
            ColorBox(modifier = Modifier.exampleLayout(0.25f).background(Color.Blue))
            ColorBox(modifier = Modifier.exampleLayout(0.5f).background(Color.Blue))
            ColorBox(modifier = Modifier.exampleLayout(0.25f).background(Color.Blue))
            ColorBox(modifier = Modifier.exampleLayout(0f).background(Color.Blue))
        }
    }
}