package com.duzi.gesturedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duzi.gesturedemo.ui.theme.ComposeRemindTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRemindTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ClickDemo()
                }
            }
        }
    }
}

@Composable
fun ClickDemo() {
    var textState by remember { mutableStateOf("waiting...") }
    var colorState by remember { mutableStateOf(true) }
    var bgColor by remember { mutableStateOf(Color.Blue) }

    val clickHandler = { status: String ->
        colorState = !colorState
        bgColor = if (colorState) Color.Blue else Color.Red
        textState = status
    }

    val pressHandler = { status: String ->
        colorState = !colorState
        bgColor = if (colorState) Color.Blue else Color.Green
        textState = status
    }

    val doubleTabHandler = { status: String ->
        colorState = !colorState
        bgColor = if (colorState) Color.Blue else Color.Black
        textState = status
    }

    val longPressHandler = { status: String ->
        colorState = !colorState
        bgColor = if (colorState) Color.Blue else Color.Cyan
        textState = status
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            Modifier
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { pressHandler("onPress Detected") },
                        onDoubleTap = { doubleTabHandler("onDoubleTab Detected") },
                        onLongPress = { longPressHandler("onLongPress Detected") },
                        onTap = { clickHandler("onTab Detected") }
                    )
                }
                .background(bgColor)
                .size(100.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(textState)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeRemindTheme {
        ClickDemo()
    }
}