package com.duzi.lazylistdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.duzi.lazylistdemo.ui.theme.ComposeRemindTheme

class MainActivity : ComponentActivity() {

    private var itemArray: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        itemArray = resources.getStringArray(R.array.car_array)

        setContent {
            ComposeRemindTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(itemArray = itemArray!!)
                }
            }
        }
    }
}

@Composable
fun MainScreen(itemArray: Array<out String>) {
    ImageLoader(item = itemArray[0])
}

@Composable
fun ImageLoader(item: String) {
    val url = "https://www.ebookfrenzy.com/book_examples/car_logos/" +
            item.substringBefore(" ") + "_logo.png"

    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = "car Image",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(75.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val itemArray: Array<String> = arrayOf("Test1", "Test2", "Test3")

    ComposeRemindTheme {
        MainScreen(itemArray = itemArray)
    }
}