package com.duzi.compose.remind.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duzi.compose.remind.ui.theme.ComposeRemindTheme

@Composable
fun CustomList(items: List<String>) {
    Column {
        for (item in items) {
            Text(item)
            Divider(color = Color.Black)
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 100
)
@Composable
fun CustomListPreview() {
    ComposeRemindTheme {
        CustomList(listOf("XXX", "YYY", "ZZZ"))
    }
}