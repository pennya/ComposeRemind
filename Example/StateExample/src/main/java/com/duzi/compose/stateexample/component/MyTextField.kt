package com.duzi.compose.stateexample.component

import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.duzi.compose.stateexample.theme.StateExampleTheme

@Composable
fun MyTextFieldStateHoisting() {
    var textState by remember { mutableStateOf("") }

    val onTextChange = { text : String ->
        textState = text
    }

    MyTextField(
        text = textState,
        onTextChange = onTextChange
    )
}

@Composable
fun MyTextField(text: String, onTextChange: (String) -> Unit) {
    // 상태가 없으므로 재사용 가능한 비상태 컴포저블
    TextField(
        value = text,
        onValueChange = onTextChange
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateExampleTheme {
        MyTextFieldStateHoisting()
    }
}