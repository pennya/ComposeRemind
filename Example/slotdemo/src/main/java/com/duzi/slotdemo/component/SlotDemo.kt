package com.duzi.slotdemo.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SlotDemo(
    topContent: @Composable () -> Unit,
    middleContent: @Composable () -> Unit,
    bottomContent: @Composable () -> Unit
) {
    Column {
        topContent()
        middleContent()
        bottomContent()
    }
}

@Composable
fun ButtonDemo() {
    Button(onClick = {}) {
        Text("Button")
    }
}

@Preview(showBackground = true)
@Composable
fun SlotDemoPreview() {
    SlotDemo(
        topContent = { Text("Top") },
        middleContent = { ButtonDemo() },
        bottomContent = { Text("Bottom") }
    )
}