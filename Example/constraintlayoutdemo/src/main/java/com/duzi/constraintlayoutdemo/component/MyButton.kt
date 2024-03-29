package com.duzi.constraintlayoutdemo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

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
            top.linkTo(parent.top)
            bottom.linkTo(button2.top)

            // 방식 1
            linkTo(parent.start, parent.end)

            // 방식 2
            //start.linkTo(parent.start)
            //end.linkTo(parent.end)
        })

        MyButton(text = "Button2", modifier = Modifier.constrainAs(button2) {
            top.linkTo(button1.bottom)
            bottom.linkTo(parent.bottom)
            linkTo(parent.start, parent.end, startMargin = 16.dp, endMargin = 16.dp)
            width = Dimension.fillToConstraints
        })
    }
}

@Composable
fun ChainScreen() {
    ConstraintLayout(Modifier.size(width = 400.dp, height = 400.dp)) {
        val (button1, button2, button3) = createRefs()

        createHorizontalChain(button1, button2, button3, chainStyle = ChainStyle.Packed)

        MyButton(text = "Button1", modifier = Modifier.constrainAs(button1) {
            centerVerticallyTo(parent)
        })

        MyButton(text = "Button2", modifier = Modifier.constrainAs(button2) {
            centerVerticallyTo(parent)
        })

        MyButton(text = "Button3", modifier = Modifier.constrainAs(button3) {
            centerVerticallyTo(parent)
        })
    }
}

@Composable
fun BarrierScreen() {
    ConstraintLayout(Modifier.size(width = 400.dp, height = 400.dp)) {
        val (button1, button2, button3) = createRefs()

        val barrier = createEndBarrier(button1, button2)

        MyButton(text = "Button1", modifier = Modifier.width(100.dp).constrainAs(button1) {
            top.linkTo(parent.top, margin = 30.dp)
            start.linkTo(parent.start, margin = 8.dp)
        })

        MyButton(text = "Button3", modifier = Modifier.constrainAs(button3) {
            linkTo(top= parent.top, bottom = parent.bottom, topMargin = 8.dp, bottomMargin = 8.dp)
            //linkTo(start = button1.end, end = parent.end, startMargin = 8.dp, endMargin = 8.dp)
            end.linkTo(parent.end, margin = 8.dp)
            start.linkTo(barrier, margin = 30.dp)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        })

        MyButton(text = "Button2", modifier = Modifier.width(200.dp).background(Color.Blue).constrainAs(button2) {
            top.linkTo(button1.bottom, margin = 20.dp)
            start.linkTo(parent.start, margin = 8.dp)
        })
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    //MainScreen()
    //ChainScreen()
    BarrierScreen()
}