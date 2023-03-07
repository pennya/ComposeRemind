package com.duzi.rowcoldemo.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun AlignBaselineComposable() {
    Column {
        Row {
            Text(
                text = "Large Text",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,

                // val LastBaseline = HorizontalAlignmentLine(::max)
                modifier = Modifier.alignBy(LastBaseline)
            )
            Text(
                text = "Small Text",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }

        Row {
            Text(
                text = "Large Text",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,

                // val FirstBaseline = HorizontalAlignmentLine(::min)
                modifier = Modifier.alignByBaseline()
            )
            Text(
                text = "Small Text",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alignByBaseline()
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlignBaselineComposablePreview() {
    AlignBaselineComposable()
}