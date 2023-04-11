package com.duzi.viewmodeldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duzi.viewmodeldemo.ui.theme.ComposeRemindTheme

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
                    ScreenSetup(DemoViewModel())
                }
            }
        }
    }
}

@Composable
fun ScreenSetup(viewModel: DemoViewModel = DemoViewModel()) {
    MainScreen(
        isFahrenheit = viewModel.isFahrenheit,
        result = viewModel.result,
        convertTemp = { viewModel.convertTemp(it) },
        switchChange = { viewModel.switchChange() }
    )
}

@Composable
fun MainScreen(
    isFahrenheit: Boolean,
    result: String,
    convertTemp: (String) -> Unit,
    switchChange: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        var textState by remember { mutableStateOf("") }
        val onTextChange = { text: String ->
            textState = text
        }

        Text(
            text = "Temperature Converter (${textState})",
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.headlineLarge
        )

        InputRow(
            isFahrenheit = isFahrenheit,
            textState = textState,
            onTextChange = onTextChange,
            switchChange = switchChange
        )

        Text(
            text = result,
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.headlineLarge
        )

        Button(onClick = { convertTemp(textState) }) {
            Text(text = "Convert Temperature")
        }
    }
}

@Composable
fun InputRow(
    isFahrenheit: Boolean,
    textState: String,
    onTextChange: (String) -> Unit,
    switchChange: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(checked = isFahrenheit, onCheckedChange = { switchChange() })

        OutlinedTextField(
            value = textState,
            onValueChange = { onTextChange(it) },
            singleLine = true,
            label = { Text(text = "Enter temperature") },
            modifier = Modifier.padding(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_ac_unit_24),
                    contentDescription = "frost",
                    modifier = Modifier.size(40.dp)
                )
            }
        )

        Crossfade(
            targetState = isFahrenheit,
            animationSpec = tween(2000)
        ) { visible ->
            when (visible) {
                true -> Text(text = "\u2109", style = MaterialTheme.typography.headlineLarge)
                false -> Text(text = "\u2103", style = MaterialTheme.typography.headlineLarge)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview(viewModel: DemoViewModel = DemoViewModel()) {
    ComposeRemindTheme {
        MainScreen(
            isFahrenheit = viewModel.isFahrenheit,
            result = viewModel.result,
            convertTemp = { viewModel.convertTemp(it) },
            switchChange = { viewModel.switchChange() }
        )
    }
}