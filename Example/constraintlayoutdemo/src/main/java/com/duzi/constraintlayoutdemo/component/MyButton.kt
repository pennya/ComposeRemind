package com.duzi.constraintlayoutdemo.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun MyButton(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier
    ) {
        Text(text)
    }
}

@Composable
fun MainScreen() {
    ConstraintLayout(Modifier.size(width = 200.dp, height = 200.dp)) {
        val (button1, button2, button3) = createRefs()
        MyButton(text = "Button1", modifier = Modifier.constrainAs(button1) {
            top.linkTo(parent.top, margin = 60.dp)
            start.linkTo(parent.start, margin = 30.dp)
        })
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}