package com.duzi.lazylistdemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
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
    val content = LocalContext.current
    val onListItemClick = { text: String ->
        Toast.makeText(content, text, Toast.LENGTH_SHORT).show()
    }

    LazyColumn {
        items(itemArray) { model ->
            MyListItem(
                item = model,
                onItemClick = onListItemClick
            )
        }
    }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyListItem(item: String, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(item) },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ImageLoader(item = item)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val itemArray: Array<String> = arrayOf("Test1", "Test2", "Test3")

    ComposeRemindTheme {
        MainScreen(itemArray = itemArray)
    }
}