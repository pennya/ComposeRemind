@file:OptIn(ExperimentalAnimationApi::class)

package com.duzi.animatevisibiltydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.KeyframesSpec
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duzi.animatevisibiltydemo.component.CustomButton
import com.duzi.animatevisibiltydemo.ui.theme.ComposeRemindTheme

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
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var boxVisible by remember { mutableStateOf(true) }

    val onClick = { newState : Boolean ->
        boxVisible = newState
    }

    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(text = "Show", targetState = true, onClick = onClick)
            CustomButton(text = "hide", targetState = false, onClick = onClick)
        }

        Spacer(modifier = Modifier.height(20.dp))

        AnimatedVisibility(
            visible = boxVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .animateEnterExit(
                            enter = fadeIn(
                                animationSpec = tween(durationMillis = 5000)
                            ),
                            exit = fadeOut(
                                animationSpec = tween(durationMillis = 5000)
                            )
                        )
                        .size(200.dp)
                        .background(color = Color.Red)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Box(
                    modifier = Modifier
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = tween(durationMillis = 5000)
                            ),
                            exit = slideOutVertically(
                                animationSpec = tween(durationMillis = 5000)
                            )
                        )
                        .size(150.dp)
                        .background(Color.Green)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeRemindTheme {
        MainScreen()
    }
}