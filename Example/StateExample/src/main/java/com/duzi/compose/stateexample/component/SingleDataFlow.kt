package com.duzi.compose.stateexample.component

import android.util.Log
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FunctionA() {
    Log.e("KJH", "FunctionA Call.")
    var switchState by remember { mutableStateOf(true) }

    val onSwitchChange = { checked: Boolean ->
        Log.e("KJH", "FunctionA.onSwitchChange Call.  before = $switchState  change : $checked")
        switchState = checked
    }

    FunctionB(switchState, onSwitchChange)
}

@Composable
fun FunctionB(switchState: Boolean, onSwitchChange: (Boolean) -> Unit) {
    Log.e("KJH", "FunctionB Call.  switchState: $switchState")
    Switch(
        checked = switchState,
        onCheckedChange = onSwitchChange
    )
}

@Preview(showBackground = true)
@Composable
fun SingleStateDataPreview() {
    FunctionA()
}