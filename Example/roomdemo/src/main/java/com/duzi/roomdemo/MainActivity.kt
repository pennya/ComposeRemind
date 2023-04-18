package com.duzi.roomdemo

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.duzi.roomdemo.model.Product
import com.duzi.roomdemo.presentation.MainViewModel
import com.duzi.roomdemo.ui.theme.ComposeRemindTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

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
                    val owner = LocalViewModelStoreOwner.current
                    owner?.let {
                        val viewModel: MainViewModel = viewModel(
                            it,
                            "MainViewModel",
                            MainViewModelFactory(
                                LocalContext.current.applicationContext as Application
                            )
                        )

                        ScreenSetup(viewModel)
                    }
                }
            }
        }
    }
}

private var index = 0

@Composable
fun ScreenSetup(viewModel: MainViewModel) {
    val products by viewModel.products.collectAsState()
    val results by viewModel.searchResults.collectAsState()
    println("products : $products")
    println("results : $results")

    Row {
        Button(
            onClick = {
                println("call insertProduct")
                viewModel.insertProduct(Product(0, "test${index++}", 1))
                runBlocking { delay(3000) }
                println("call findProduct")
                viewModel.findProduct("test0")
            },
            modifier = Modifier
                .size(100.dp, 50.dp)
                .padding(10.dp)
        ) {
            Text(text = "Insert")
        }

        Spacer(modifier = Modifier.width(10.dp))

        Button(
            onClick = {
                println("call deleteProduct")
                viewModel.deleteProduct("test0")
                runBlocking { delay(3000) }
                println("call findProduct")
                viewModel.findProduct("test1")
            },
            modifier = Modifier
                .size(100.dp, 50.dp)
                .padding(10.dp)
        ) {
            Text(text = "Delete")
        }
    }
}

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {

}

@Composable
fun TitleRow(head1: String, head2: String, head3: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Text(
            text = head1,
            color = Color.White,
            modifier = Modifier.weight(0.1f),
        )
        Text(
            text = head2,
            color = Color.White,
            modifier = Modifier.weight(0.2f),
        )
        Text(
            text = head3,
            color = Color.White,
            modifier = Modifier.weight(0.2f),
        )

    }
}

@Composable
fun ProductRow(id: Int, name: String, quantity: Int) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Text(
            id.toString(),
            modifier = Modifier.weight(0.1f),
        )
        Text(
            name,
            modifier = Modifier.weight(0.2f),
        )
        Text(
            quantity.toString(),
            modifier = Modifier.weight(0.2f),
        )
    }
}

@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        label = { Text(text = title) },
        modifier = Modifier.padding(10.dp),
        textStyle = androidx.compose.ui.text.TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    )
}

// TODO hilt migration
class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(application) as T
    }
}