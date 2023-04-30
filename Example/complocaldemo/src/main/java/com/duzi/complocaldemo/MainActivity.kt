package com.duzi.complocaldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.duzi.complocaldemo.ui.theme.ComposeRemindTheme

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
                    Composable1()
                }
            }
        }
    }
}

val LocalColor = compositionLocalOf { Color(0xFFffdbcf) }

@Composable
fun Composable1() {
    val color = if (isSystemInDarkTheme()) {
        Color(0xFFa08d87)
    } else {
        Color(0xFFffdbcf)
    }

    Column {
        Composable2()

        CompositionLocalProvider(LocalColor provides color) {
            Composable3()
        }
    }
}

@Composable
fun Composable2() {
    Composable4()
}

@Composable
fun Composable3() {
    Text(
        text = "Composable 3",
        modifier = Modifier.background(LocalColor.current)
    )

    CompositionLocalProvider(LocalColor provides Color.Red) {
        Composable5()
    }
}

@Composable
fun Composable4() {
    Composable6()
}

@Composable
fun Composable5() {
    Text(
        text = "Composable 5",
        modifier = Modifier.background(LocalColor.current)
    )

    CompositionLocalProvider(LocalColor provides Color.Blue) {
        Composable7()
    }
    
    CompositionLocalProvider(LocalColor provides Color.Green) {
        Composable8()
    }
}

@Composable
fun Composable6() {
    Text(
        text = "Composable 6",
        modifier = Modifier.background(LocalColor.current)
    )
}

@Composable
fun Composable7() {
    Text(
        text = "Composable 7",
        modifier = Modifier.background(LocalColor.current)
    )
}

@Composable
fun Composable8() {
    Text(
        text = "Composable 8",
        modifier = Modifier.background(LocalColor.current)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeRemindTheme {
        Composable1()
    }
}