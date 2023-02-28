package com.duzi.slotdemo.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duzi.slotdemo.R

@Composable
fun MainScreen() {
    var linearSelected by remember { mutableStateOf(true) }
    var imageSelected by remember { mutableStateOf(true) }

    val onLinearClick = { value : Boolean ->
        linearSelected = value
    }

    val onTitleClick = { value : Boolean ->
        imageSelected = value
    }

    ScreenContent(
        linearSelected = linearSelected,
        imageSelected = imageSelected,
        onLinearClick = onLinearClick,
        onTitleClick = onTitleClick,
        titleContent = {
            if (imageSelected) {
                TitleImage(drawing = R.drawable.baseline_cloud_download)
            } else {
                Text(
                    text = "Downloading",
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(30.dp)
                )
            }
        },
        progressContent =  {
            if (linearSelected) {
                LinearProgressIndicator(Modifier.height(40.dp))
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.size(200.dp),
                    strokeWidth = 18.dp
                )
            }
        }
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MainScreenPreview() {
    MainScreen()
}