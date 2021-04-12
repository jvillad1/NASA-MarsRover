package com.jvillad1.marsrover.ui.screens.rovers.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jvillad1.marsrover.R
import com.jvillad1.marsrover.ui.model.RoverUI
import com.jvillad1.marsrover.ui.theme.MarsRoverTheme
import com.jvillad1.marsrover.ui.theme.RoverGradient

@Composable
fun RoverCard(
    rover: RoverUI,
    onRoverClick: (Int) -> Unit = {}
) {
    val imageModifier = if (rover.id == 1) {
        Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .height(60.dp)
            .width(60.dp)
            .clip(CircleShape)
            .border(
                shape = CircleShape,
                border = BorderStroke(
                    width = 3.dp,
                    color = Color.LightGray
                )
            )
    } else {
        Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .height(60.dp)
            .width(60.dp)
            .clip(CircleShape)
            .border(
                shape = CircleShape,
                border = BorderStroke(
                    width = 3.dp,
                    brush = Brush.linearGradient(
                        colors = RoverGradient,
                        start = Offset(
                            0f,
                            0f
                        ),
                        end = Offset(
                            100f,
                            100f
                        )
                    )
                )
            )
    }

    Column(
        modifier = Modifier.clickable {
            onRoverClick(rover.id)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.mars_globe),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = imageModifier
        )
        Text(
            text = rover.name,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = typography.caption
        )
    }
}

@Preview
@Composable
fun RoverCardPreview() {
    MarsRoverTheme() {
        RoverCard(
            rover = RoverUI(
                id = 1,
                name = "Curiosity",
                landingDate = "",
                launchDate = "2021-04-06",
                status = "",
                maxSol = 0,
                total_photos = 0,
                cameras = emptyList(),
            )
        )
    }
}
