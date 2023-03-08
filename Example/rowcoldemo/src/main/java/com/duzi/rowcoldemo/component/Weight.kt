package com.duzi.rowcoldemo.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Content() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    //BoxWithConstraints
    Box(
        propagateMinConstraints = false,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(150.dp)
                .border(2.dp, color = Color.Black)
        ) {
            Text(text = "12123123")
        }
    }
}