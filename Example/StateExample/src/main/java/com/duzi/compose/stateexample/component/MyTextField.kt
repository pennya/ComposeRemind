package com.duzi.compose.stateexample.component

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.duzi.compose.stateexample.theme.StateExampleTheme

@Composable
fun MyTextFieldStateHoisting() {
    // rememberSaveable을 사용하여 상태를 저장할 수 있음 (환경 설정 변경 시에도 유지)
    var textState by rememberSaveable { mutableStateOf("") }

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