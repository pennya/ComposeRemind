package com.duzi.compose.remind.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.duzi.compose.remind.ui.theme.ComposeRemindTheme

@Composable
fun CustomSwitch() {
    val checked = remember { mutableStateOf(true) }

    Column {
        Switch(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )

        if (checked.value) {
            Text("Switch is On")
        } else {
            Text("Switch is Off")
        }
    }
}

@Preview(
    name = "CustomSwitch",
    showBackground = true
)
@Composable
fun CustomSwitchPreview() {
    ComposeRemindTheme {
        CustomSwitch()
    }
}