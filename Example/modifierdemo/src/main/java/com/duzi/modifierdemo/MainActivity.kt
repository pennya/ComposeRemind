package com.duzi.modifierdemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duzi.modifierdemo.component.CustomImage
import com.duzi.modifierdemo.ui.theme.ComposeRemindTheme

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
                    DemoScreen()
                }
            }
        }
    }
}

@Composable
fun DemoScreen() {
    val context = LocalContext.current

    val modifier = Modifier
        .padding(all = 10.dp)
        .border(width = 2.dp, color = Color.Blue)

    // 모디파이어 순서 영향도.
    val modifier2 = Modifier
        .border(width = 3.dp, color = Color.Red)
        .padding(all = 10.dp)

    Column(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .padding(5.dp)
            .border(width = 2.dp, color = Color.Green),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Hello!",
            modifier = modifier,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Hello Compose!",
            modifier = modifier2,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(10.dp)
                .border(
                    width = 2.dp,
                    color = Color.DarkGray,
                    shape = RoundedCornerShape(
                        corner = CornerSize(10.dp)
                    )
                )
                .background(
                    color = Color.Cyan,
                    shape = RoundedCornerShape(
                        corner = CornerSize(10.dp)
                    )
                )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .border(2.dp, Color.Black)
                .clickable {
                    // do something
                    Toast
                        .makeText(context, "Clicked!", Toast.LENGTH_SHORT)
                        .show()
                },
            contentAlignment = Alignment.Center
        ) {
            CustomImage(
                image = R.drawable.arsenal,
                modifier = Modifier
                    .size(200.dp, 100.dp)
                    .alpha(0.3f)
            )
            Spacer(modifier = Modifier.size(width = 0.dp, height = 10.dp))
            Text(
                text = "Arsenal",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.TopStart)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ComposeRemindTheme {
        DemoScreen()
    }
}