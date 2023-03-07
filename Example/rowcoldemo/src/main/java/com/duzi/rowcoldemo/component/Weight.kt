package com.duzi.rowcoldemo.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.duzi.rowcoldemo.TextCell

@Composable
fun WeightComposable() {
    Row {
        TextCell(text = "1", Modifier.weight(0.2f, true))
        TextCell(text = "1", Modifier.weight(0.4f, true))
        TextCell(text = "1", Modifier.weight(0.3f, true))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeightComposablePreview() {
    WeightComposable()
}