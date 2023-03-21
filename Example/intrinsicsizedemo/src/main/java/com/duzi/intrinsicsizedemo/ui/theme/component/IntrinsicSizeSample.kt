package com.duzi.intrinsicsizedemo.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IntrinsicSizeSample() {
    Row(modifier = Modifier.height(IntrinsicSize.Max).background(Color.Yellow)) {
        Box(modifier = Modifier.size(100.dp).background(color = Color.Red))
        Box(modifier = Modifier.size(10.dp).background(color = Color.Blue))
        Box(modifier = Modifier.size(200.dp).background(color = Color.Cyan))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IntrinsicSizeSamplePreview() {
    IntrinsicSizeSample()
}
