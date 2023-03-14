package com.duzi.constraintlayoutdemo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Preview(showBackground = true)
@Composable
fun ConstraintLayoutPreview() {
    ConstraintLayout(
        Modifier
            .size(width = 200.dp, height = 200.dp)
            .background(Color.Green)
    ) {
        val text1 = createRef()
        val text2 = createRef()
        val text3 = createRef()
        val text4 = createRef()
        val text5 = createRef()

        Text("Hello", modifier = Modifier.constrainAs(text1) {
            top.linkTo(parent.top, margin = 16.dp)
            bottom.linkTo(parent.bottom, margin = 16.dp)
        })

        Text("Hello2", modifier = Modifier.constrainAs(text2) {
            linkTo(parent.top, parent.bottom, bias = 0.8f)
            linkTo(text1.start, text1.end)
        })

        Text("Hello3\nHello3\nHello3\nHello3\nHello3", modifier = Modifier.constrainAs(text3) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(text1.end, margin = 16.dp)
            end.linkTo(parent.end)
        })
        
        Text("Hello4", modifier = Modifier.constrainAs(text4) {
            centerVerticallyTo(text3)
            centerHorizontallyTo(parent)
        })

        Text("Hello5", modifier = Modifier.constrainAs(text5) {
            centerAround(text4.start)
            centerAround(text2.top)
        })
    }
}