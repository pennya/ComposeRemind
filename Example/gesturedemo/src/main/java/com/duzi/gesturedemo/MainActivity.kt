package com.duzi.gesturedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
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
                    MultiTouchDemo()
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

@Composable
fun ScrollableModifiers() {

    val image = ImageBitmap.imageResource(id = R.drawable.android_icon)

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Green)) {
        Box(
            Modifier
                .size(150.dp)
                .verticalScroll(rememberScrollState())
                .horizontalScroll(rememberScrollState())
        ) {
            Canvas(
                modifier = Modifier.size(360.dp, 270.dp)
            ) {
                drawImage(
                    image = image,
                    topLeft = Offset(0f, 0f)
                )
            }
        }
    }
}

@Composable
fun ScrollableModifier() {
    var offset by remember { mutableStateOf(0f) }

    Box(
        Modifier
            .fillMaxSize()
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState { delta ->
                    offset += delta
                    delta
                }
            )
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(0, offset.roundToInt()) }
                .size(100.dp)
                .background(Color.Blue)
        )
    }
}

@Composable
fun MultiTouchDemo() {
    var scale by remember { mutableStateOf(1f) }
    var angle by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        angle += rotationChange
        offset += offsetChange
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier.graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = angle,
                translationX = offset.x,
                translationY = offset.y
            )
                .transformable(state = state)
                .background(Color.Blue)
                .size(100.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeRemindTheme {
        ScrollableModifiers()
    }
}