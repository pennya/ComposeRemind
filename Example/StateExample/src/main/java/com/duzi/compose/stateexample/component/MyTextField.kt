package com.duzi.compose.stateexample.component

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.duzi.compose.stateexample.theme.StateExampleTheme

@Composable
fun MyTextField() {
    val (textValue, setText) = remember { mutableStateOf("") }

    val onTextChange = { text : String ->
        setText(text)
    }

    TextField(
        value = textValue,
        onValueChange = onTextChange
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateExampleTheme {
        MyTextField()
    }
}