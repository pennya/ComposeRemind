package com.duzi.gesturedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.duzi.gesturedemo.ui.theme.ComposeRemindTheme
import kotlin.math.roundToInt

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
                    DragDemo()
                }
            }
        }
    }
}

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }


@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

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

@Composable
fun DragDemo() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .clipToBounds()
    ) {
        var xOffset by remember { mutableStateOf(0f) }
        val parentWidth = constraints.maxWidth

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            val boxSize = 100.dp

            Box(
                Modifier
                    .offset { IntOffset(xOffset.roundToInt(), 0) }
                    .size(boxSize)
                    .background(Color.Blue)
                    .align(Alignment.Start)
                    .pointerInput(Unit) {
                        val boxSize = this.size
                        detectDragGestures { change, dragAmount ->
                            xOffset = (xOffset + dragAmount.x).coerceIn(
                                0f,
                                parentWidth.toFloat() - boxSize.width.toFloat()
                            )
                        }
                    }
                    /*.draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta ->
                            xOffset = (xOffset + delta).coerceIn(
                                0f,
                                parentWidth.toFloat()
                            )
                        }
                    )*/
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text("xOffset: $xOffset")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeRemindTheme {
        DragDemo()
    }
}