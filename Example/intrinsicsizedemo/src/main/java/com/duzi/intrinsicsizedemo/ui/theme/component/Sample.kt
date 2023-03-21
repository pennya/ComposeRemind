package com.duzi.intrinsicsizedemo.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyTextField(text: String, onTextChange: (String) -> Unit) {
    TextField(value = text, onValueChange = onTextChange)
}

@Composable
fun MainScreen() {

    var textState by remember { mutableStateOf("") }

    val onTextChange = { text : String ->
        textState = text
    }

    Column(modifier = Modifier.background(Color.Green)) {
        Column(
            Modifier
                .width(200.dp)
                .padding(5.dp)
                .background(Color.Yellow)) {
            Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp),
                    text = textState
                )

                Box(
                    Modifier
                        .height(10.dp)
                        .fillMaxSize()
                        .background(Color.Blue)
                )
            }

            MyTextField(text = textState, onTextChange = onTextChange)
        }

        Column(
            Modifier
                .width(200.dp)
                .padding(5.dp)
                .background(Color.Magenta)) {
            Column(modifier = Modifier.width(IntrinsicSize.Min)) {
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp),
                    text = textState
                )

                Box(
                    Modifier
                        .height(10.dp)
                        .fillMaxSize()
                        .background(Color.Blue)
                )
            }

            MyTextField(text = textState, onTextChange = onTextChange)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}