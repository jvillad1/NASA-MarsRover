package com.jvillad1.marsrover.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ScreenTitleText(title: String, style: TextStyle = MaterialTheme.typography.h4) {
    Text(
        text = title,
        style = style,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        color = Color.White,
        textAlign = TextAlign.Center
    )
}
