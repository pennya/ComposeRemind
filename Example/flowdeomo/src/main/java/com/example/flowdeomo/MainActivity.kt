package com.example.flowdeomo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flowdeomo.ui.theme.ComposeRemindTheme

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
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "screen1") {
                        composable("screen1") {
                            val viewModel = viewModel<ScreenViewModel>()
                            val time by viewModel.timer.collectAsState()
                            Screen1(
                                time = time
                            ) {
                                navController.navigate("screen2")
                            }
                        }
                        composable("screen2") {
                            Screen2()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Screen1(time: Int, onButtonClick: () -> Unit) {
    Column {
        Text(time.toString())
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = onButtonClick) {
            Text(text = "Go to Screen 2")
        }
    }
}

@Composable
fun Screen2() {
    Text(text = "Screen 2")
}