package com.duzi.roomdemo

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

@Composable
fun ScreenSetup(viewModel: MainViewModel) {
    val products by viewModel.products.collectAsState(listOf())
    val results by viewModel.searchResults.collectAsState(listOf())
    println("products : $products")
    println("results : $results")

    MainScreen(
        products = products,
        results = results,
        viewModel = viewModel
    )

    /*
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
    */
}

@Composable
fun MainScreen(
    products: List<Product>,
    results: List<Product>,
    viewModel: MainViewModel,
) {
    var productName by remember { mutableStateOf("") }
    var productQuantity by remember { mutableStateOf("") }
    var searching by remember { mutableStateOf(false) }

    val onProductTextChange = { text: String ->
        productName = text
    }

    val onQuantityTextChange = { text: String ->
        productQuantity = text
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CustomTextField(
            title = "Product Name",
            textState = productName,
            onTextChange = onProductTextChange,
            keyboardType = KeyboardType.Text
        )
        CustomTextField(
            title = "Quantity",
            textState = productQuantity,
            onTextChange = onQuantityTextChange,
            keyboardType = KeyboardType.Number
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(onClick = {
                if (productQuantity.isNotEmpty()) {
                    viewModel.insertProduct(
                        Product(
                            productName = productName,
                            quantity = productQuantity.toInt()
                        )
                    )
                    searching = false
                }
            }) {
                Text(text = "Add")
            }

            Button(onClick = {
                searching = true
                viewModel.findProduct(productName)
            }) {
                Text(text = "Search")
            }

            Button(onClick = {
                searching = false
                viewModel.deleteProduct(productName)
            }) {
                Text(text = "Delete")
            }

            Button(onClick = {
                searching = false
                productName = ""
                productQuantity = ""
            }) {
                Text(text = "Clear")
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            val list = if (searching) results else products

            item {
                TitleRow(head1 = "ID", head2 = "Product", head3 = "Quantity")
            }

            itemsIndexed(list) { index, product ->
                ProductRow(
                    id = product.id,
                    name = product.productName,
                    quantity = product.quantity
                )
            }
        }
    }
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

@OptIn(ExperimentalMaterial3Api::class)
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
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(application) as T
    }
}