package com.duzi.rowcoldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duzi.rowcoldemo.ui.theme.ComposeRemindTheme

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .size(100.dp, 400.dp)
                    .padding(4.dp)
                    .background(Color.Blue),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                TextCell(
                    text = "1",
                    modifier = Modifier
                        .padding(all = 4.dp)
                        .size(100.dp, 100.dp)
                        .border(width = 2.dp, color = Color.Red)
                )
                TextCell(text = "2")
                TextCell(text = "3")
            }

            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color.LightGray)
            ) {
                TextCell(text = "4")
                TextCell(text = "5")
                TextCell(text = "6")
            }

            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color.Magenta)
            ) {
                TextCell(text = "7")
                TextCell(text = "8")
                TextCell(text = "9")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Green)
        ) {
            TextCell(text = "10", Modifier.align(Alignment.Top))
            TextCell(text = "11", Modifier.align(Alignment.CenterVertically))
            TextCell(text = "12", Modifier.align(Alignment.Bottom))
        }
    }
}

@Composable
fun TextCell(text: String, modifier: Modifier = Modifier) {
    val cellModifier = Modifier
        .padding(all = 4.dp)
        .size(100.dp, 100.dp)
        .border(width = 4.dp, color = Color.Black)

    Text(
        text = text,
        modifier = cellModifier.then(modifier),
        fontSize = 70.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ComposeRemindTheme {
        MainScreen()
    }
}