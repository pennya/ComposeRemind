package com.duzi.slotdemo.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun TitleImage(drawing: Int) {
    Image(
        painter = painterResource(id = drawing),
        contentDescription = "title image"
    )
}