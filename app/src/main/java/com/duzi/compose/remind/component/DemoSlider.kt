package com.duzi.compose.remind.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duzi.compose.remind.ui.theme.ComposeRemindTheme

@Composable
fun DemoSlider(sliderPosition: Float, onPositionChange : (Float) -> Unit) {
    Slider(
        modifier = Modifier.padding(10.dp),
        valueRange = 20f..40f,
        value = sliderPosition,
        onValueChange = onPositionChange
    )
}

@Preview(
    name = "DemoSlider",
    showSystemUi = true
)
@Composable
fun DemoSliderPreview() {
    ComposeRemindTheme {
        DemoSlider(30f) { value ->
            println("position : $value")
        }
    }
}